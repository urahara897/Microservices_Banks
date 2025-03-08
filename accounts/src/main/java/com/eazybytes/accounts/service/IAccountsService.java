package com.eazybytes.accounts.service;

import com.eazybytes.accounts.dto.CustomerDto;
import org.springframework.stereotype.Service;

@Service
public interface IAccountsService {

    /**
     * Creates a new account for the provided customer.
     *
     * @param customerDto the data transfer object containing customer details
     */
    void createAccount(CustomerDto customerDto);

    /**
     * Fetches the account details associated with the given mobile number.
     *
     * @param mobileNumber the mobile number of the customer whose account details are to be fetched
     * @return CustomerDto containing the details of the customer's account, or null if no account is found
     */
    CustomerDto fetchAccountDetails(String mobileNumber);

    /**
     * Updates the account details for the provided customer.
     *
     * @param customerDto the data transfer object containing updated customer details
     * @return true if the account update was successful, false otherwise
     */
    boolean updateAccount(CustomerDto customerDto);

    /**
     *
     * @param mobileNumber - Input Mobile Number
     * @return boolean indicating if the delete of Account details is successful or not
     */
    boolean deleteAccount(String mobileNumber);

    /**
     *
     * @param accountNumber - Long
     * @return boolean indicating if the update of communication status is successful or not
     */
    boolean updateCommunicationStatus(Long accountNumber);
}
