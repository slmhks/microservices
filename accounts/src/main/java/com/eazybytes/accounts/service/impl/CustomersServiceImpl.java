package com.eazybytes.accounts.service.impl;

import com.eazybytes.accounts.dto.*;
import com.eazybytes.accounts.entity.Accounts;
import com.eazybytes.accounts.entity.Customer;
import com.eazybytes.accounts.exception.ResourceNotFoundException;
import com.eazybytes.accounts.mapper.AccountsMapper;
import com.eazybytes.accounts.mapper.CustomerMapper;
import com.eazybytes.accounts.repository.AccountsRepository;
import com.eazybytes.accounts.repository.CustomerRepository;
import com.eazybytes.accounts.service.ICustomersService;
import com.eazybytes.accounts.service.client.CardsFeignClient;
import com.eazybytes.accounts.service.client.LoansFeignClient;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CustomersServiceImpl implements ICustomersService {

    private CustomerRepository customerRepository;
    private AccountsRepository accountsRepository;
    private CardsFeignClient cardsFeignClient;
    private LoansFeignClient loansFeignClient;

    /**
     * This method get the customer and its associated accounts, cards and loans
     *
     * @param mobileNumber mobile number composed of 10 digits
     * @param correlationId correlation id for logging purposes
     * @return customer details dto
     */
    @Override
    public CustomerDetailsDto fetchCustomerDetails(String correlationId, String mobileNumber) {
        Customer customer = this.customerRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException("customer", "mobileNumber", mobileNumber)
        );
        Accounts accounts = this.accountsRepository.findByCustomerId(customer.getCustomerId()).orElseThrow(
                () -> new ResourceNotFoundException("account", "customerId", customer.getCustomerId().toString())
        );
        ResponseEntity<CardDto> responseCardDto = this.cardsFeignClient.fetchCardDetails(correlationId, mobileNumber);
        ResponseEntity<LoansDto> responseLoansDto = this.loansFeignClient.fetchLoanDetails(correlationId, mobileNumber);

        // Convert to Customer Details
        CustomerDto customerDto = CustomerMapper.mapToCustomerDto(customer, new CustomerDto());
        AccountsDto accountsDto = AccountsMapper.mapToAccountsDto(accounts, new AccountsDto());

        CustomerDetailsDto customerDetailsDto = CustomerMapper.mapToCustomerDetailsDto(customerDto, new CustomerDetailsDto());

        customerDetailsDto.setAccountsDto(accountsDto);
        if (responseCardDto != null) {
            CardDto cardDto = responseCardDto.getBody();
            customerDetailsDto.setCardDto(cardDto);
        }
        if (responseLoansDto != null) {
            LoansDto loansDto = responseLoansDto.getBody();
            customerDetailsDto.setLoansDto(loansDto);
        }

        return customerDetailsDto;
    }
}
