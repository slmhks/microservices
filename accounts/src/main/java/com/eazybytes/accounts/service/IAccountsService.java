package com.eazybytes.accounts.service;

import com.eazybytes.accounts.dto.CustomerDto;

public interface IAccountsService {

    /**
     * Create account
     * @param customerDto
     */
    public void createAccount(CustomerDto customerDto);

    /**
     * This method returns a customer found by mobile number.
     * @param mobileNumber
     * @return customer information
     */
    public CustomerDto fetchAccount(String mobileNumber);

    /**
     * Updates an account
     * @param customerDto
     * @return return true when the account is updated successfully
     */
    public boolean updateAccount(CustomerDto customerDto);

    /**
     * This method deletes the customer account.
     * @param mobileNumber
     */
    public boolean deleteAccount(String mobileNumber);
}
