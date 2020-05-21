package com.butch.spring_cloud_provider_8002.controller;

import java.util.List;

import com.butch.spring_cloud_api.pojo.User;
import com.butch.spring_cloud_provider_8002.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private DiscoveryClient discoveryClient;
    @GetMapping("/provider/user")
    public User user(){
        return userService.getUser();
    }

    @GetMapping("/provider/info")
    public List<String> getEurekaInfo(){
        return discoveryClient.getServices();
    }
    
}