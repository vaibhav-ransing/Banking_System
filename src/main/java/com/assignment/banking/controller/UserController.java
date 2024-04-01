package com.assignment.banking.controller;

import com.assignment.banking.entity.User;
import com.assignment.banking.model.UserRequest;
import com.assignment.banking.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/create")
    public ResponseEntity<User> createAccount(@RequestBody UserRequest userRequest) {
        User user
                = userService.createUser(userRequest);

        return ResponseEntity.ok(user);
    }

    @GetMapping("/{username}")
    public ResponseEntity<List<String>> getUserAccounts(@PathVariable String username) {
        List<String> userAccounts = userService.getUserAccounts(username);
        return ResponseEntity.ok(userAccounts);
    }
}
