package com.assignment.banking.service;

import com.assignment.banking.entity.Account;
import com.assignment.banking.entity.User;
import com.assignment.banking.exception.custom.AccountNotFound;
import com.assignment.banking.exception.custom.CustomException;
import com.assignment.banking.model.AccountRequest;
import com.assignment.banking.repository.AccountRepository;
import com.assignment.banking.repository.UserRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.Optional;

@Service
@Log4j2
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public String createAccount(AccountRequest accountRequest) {

        log.info("Creating account");
        User user = userRepository.findByUsername(accountRequest.getUserName())
                .orElseThrow(() -> new RuntimeException("User not found with userName= " + accountRequest.getUserName()));

        Account account = Account.builder()
                .accountNumber(accountRequest.getAccountNumber())
                .balance(accountRequest.getBalance())
                .user(user)
                .build();
        accountRepository.save(account);
        log.info("Account successfully created for " + accountRequest.getUserName());

        return "Account Successfully created " + account.getAccountNumber();
    }


    @Override
    public String deposit(String accountNumber, double amount) {
        log.info(MessageFormat.format("Depositing money from {0} by amount {1}", accountNumber, amount));


        Account account = findAccountByAccountNumber(accountNumber);
        account.setBalance(account.getBalance() + amount);
        accountRepository.save(account);

        log.info("Amount successfully deposited");

        return "Balance deposited. Current balance: " + account.getBalance();
    }

    @Override
    public String withdraw(String accountNumber, double amount) {

        log.info(MessageFormat.format("Withdrawing money from {0} by amount {1}", accountNumber, amount));

        Account account = findAccountByAccountNumber(accountNumber);

        double remainingBalance = Optional.of(account)
                .filter(account1 -> account1.getBalance() >= amount)
                .map(account1 -> account1.getBalance() - amount)
                .orElseThrow(() -> new CustomException("Insufficient Balance"));
        account.setBalance(remainingBalance);
        accountRepository.save(account);

        log.info("Amount successfully Withdraw");

        return "Withdrawal successful. Remaining balance: " + account.getBalance();

    }

    @Override
    public String transfer(String fromAccountNumber, String toAccountNumber, double amount) {

        log.info(MessageFormat.format("Transferring money from account {0} to {1} by amount {2}"
                , fromAccountNumber, toAccountNumber, amount));

        withdraw(fromAccountNumber, amount);
        deposit(toAccountNumber, amount);

        log.info("Amount successfully transferred");

        return "Amount Successfully transferred";
    }


    private Account findAccountByAccountNumber(String accountNumber) {
        return accountRepository.findByAccountNumber(accountNumber)
                .orElseThrow(() -> new AccountNotFound("Account not found: " + accountNumber));
    }
}
