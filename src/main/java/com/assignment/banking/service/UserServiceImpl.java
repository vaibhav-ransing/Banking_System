package com.assignment.banking.service;

import com.assignment.banking.entity.Account;
import com.assignment.banking.entity.User;
import com.assignment.banking.exception.custom.UserAlreadyPresent;
import com.assignment.banking.model.UserRequest;
import com.assignment.banking.repository.AccountRepository;
import com.assignment.banking.repository.UserRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Log4j2
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public User createUser(UserRequest userRequest) {

        log.info("Creating new user " + userRequest.getUsername());

        /* If user is already present then throw exception */
        userRepository.findByUsername(userRequest.getUsername())
                .ifPresent(u -> {
                    throw new UserAlreadyPresent("User already present " + userRequest.getUsername());
                });

        /* Create a User object to save in database*/
        User user
                = User.builder()
                .password(userRequest.getPassword())
                .username(userRequest.getUsername())
                .build();

        userRepository.save(user);

        log.info("Successfully created new user " + user.getId());
        return user;
    }

    @Override
    public List<String> getUserAccounts(String username) {

        log.info("Getting all accounts for user " + username);

        /* So we first filter out all usernames and then use map over that to only return the accountNumber */
        return accountRepository.findAll()
                .stream()
                .filter(account -> account.getUser().getUsername().equals(username)).map(
                        Account::getAccountNumber    /* account -> {return account.getAccountNumber();} */
                ).toList();
    }
}



/*      // A way to use functional interface Predicates
        // If user is already present.

        Predicate<UserRequest> userAlreadyPresent = user -> userRepository.findByUsername(user.getUsername()).isPresent();
        if (userAlreadyPresent.test(userRequest)) {
            throw new UserAlreadyPresent("User already present " + userRequest.getUsername());
        }
*/