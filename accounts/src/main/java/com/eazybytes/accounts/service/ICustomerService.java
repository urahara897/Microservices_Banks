package com.eazybytes.accounts.service;

import com.eazybytes.accounts.dto.CustomerDetailsDto;

public interface ICustomerService {

    /**
     * Creates a new account for the provided customer.
     *
     * @param mobileNumber Input Mobile Number
     * @return Customer Details based on mobile number
     */
    CustomerDetailsDto fetchCustomerDetails(String mobileNumber);
}
