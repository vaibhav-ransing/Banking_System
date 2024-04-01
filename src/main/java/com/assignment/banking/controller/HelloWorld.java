package com.assignment.banking.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorld {


    @GetMapping("")
    public String home(){
        return "Home Page";
    }

    @GetMapping("/hello")
    public String hello(){
        return "Hello World";
    }
}