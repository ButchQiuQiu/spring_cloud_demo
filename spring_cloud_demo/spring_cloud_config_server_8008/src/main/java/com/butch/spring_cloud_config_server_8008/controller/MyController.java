package com.butch.spring_cloud_config_server_8008.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyController {
    @GetMapping("/test")
    public String test(){
        return "test";
    }
}