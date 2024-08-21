package com.eazybytes.accounts.service;

import com.eazybytes.accounts.dto.CustomerDetailsDto;

/**
 * This interface retrieves the customer and its associated accounts, cards and loans.
 */
public interface ICustomersService {

    /**
     * This method get the customer and its associated accounts, cards and loans
     * @param correlationId correlation id for logging purposes
     * @param mobileNumber mobile number composed of 10 digits
     * @return customer details dto
     */
    public CustomerDetailsDto fetchCustomerDetails(String correlationId, String mobileNumber);
}
