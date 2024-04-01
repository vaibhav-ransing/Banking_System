package com.assignment.banking.controller;

import com.assignment.banking.model.AccountRequest;
import com.assignment.banking.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/accounts")
public class AccountController {
    @Autowired
    private AccountService accountService;

    @PostMapping("/create")
    public ResponseEntity<String> createAccount(@RequestBody AccountRequest accountRequest) {

        String response = accountService.createAccount(accountRequest);
        return ResponseEntity.ok(response);
    }


    @PostMapping("/deposit")
    public ResponseEntity<String> deposit(@RequestParam String accountNumber,
                                          @RequestParam double amount) {

        String status = accountService.deposit(accountNumber, amount);
        return ResponseEntity.ok(status);
    }

    @PostMapping("/withdraw")
    public ResponseEntity<String> withdraw(@RequestParam String accountNumber,
                                           @RequestParam double amount) {

        String status = accountService.withdraw(accountNumber, amount);
        return ResponseEntity.ok(status);
    }

    @PostMapping("/transfer")
    public ResponseEntity<String> transfer(@RequestParam String fromAccountNumber,
                                           @RequestParam String toAccountNumber,
                                           @RequestParam double amount) {

        String status = accountService.transfer(fromAccountNumber, toAccountNumber, amount);
        return ResponseEntity.ok(status);
    }
}