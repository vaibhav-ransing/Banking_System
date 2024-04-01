package com.assignment.banking.service;

import com.assignment.banking.model.AccountRequest;


public interface AccountService {
    String deposit(String accountNumber, double amount);

    String withdraw(String accountNumber, double amount);

    String transfer(String fromAccountNumber, String toAccountNumber, double amount);


    String createAccount(AccountRequest accountRequest);
}
