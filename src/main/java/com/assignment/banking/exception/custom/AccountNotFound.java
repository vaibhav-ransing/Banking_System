package com.assignment.banking.exception.custom;

public class AccountNotFound  extends RuntimeException{

    public AccountNotFound(String message){
        super(message);
    }
}
