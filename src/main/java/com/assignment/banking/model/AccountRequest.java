package com.assignment.banking.model;

import lombok.Data;

@Data
public class AccountRequest {
    private String userName;  // we will fetch User from JPA
    private String accountNumber;
    private double balance;
}
