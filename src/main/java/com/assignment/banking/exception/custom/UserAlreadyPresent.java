package com.assignment.banking.exception.custom;

public class UserAlreadyPresent extends RuntimeException{

    public UserAlreadyPresent(String message){
        super(message);
    }
}
