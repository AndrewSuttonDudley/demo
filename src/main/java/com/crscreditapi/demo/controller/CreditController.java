package com.crscreditapi.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/credit")
public class CreditController {

    @GetMapping("/requests/{requestId}")
    public String getCreditRequestById(@PathVariable("requestId") String requestId) {
        return "Hello, World!";
    }
}
