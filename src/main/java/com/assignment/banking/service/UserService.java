package com.assignment.banking.service;

import com.assignment.banking.entity.User;
import com.assignment.banking.model.UserRequest;
import org.springframework.stereotype.Service;

import java.util.List;


public interface UserService {

    User createUser(UserRequest userRequest);

    List<String> getUserAccounts(String username);
}
