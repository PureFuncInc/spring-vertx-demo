package com.example.demo.service;

import org.springframework.stereotype.Service;

@Service
public class SampleService {

    public String process(String input) {
        if (input.equals("ping")) {
            return "pong";
        } else {
            return input.toUpperCase();
        }
    }
}
