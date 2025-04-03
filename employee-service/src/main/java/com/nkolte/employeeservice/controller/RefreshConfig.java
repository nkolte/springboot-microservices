package com.nkolte.employeeservice.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RefreshScope
public class RefreshConfig {
    @Value("${welcome.message}")
    private String message;

    @GetMapping("/refresh-config")
    public String getMessage(){
        return message;
    }
}

