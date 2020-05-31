package com.butch.spring_cloud_provider_8001.controller;

import java.util.List;

import com.butch.spring_cloud_api.pojo.User;
import com.butch.spring_cloud_provider_8001.service.UserService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RefreshScope
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private DiscoveryClient discoveryClient;
    @Value("${word}")
    private String word;

    @GetMapping("/config/word")
    public String word(){
        return word;
    }

    @GetMapping("/provider/user")
    public User user(){
        return userService.getUser();
    }

    @GetMapping("/provider/info")
    public List<String> getEurekaInfo(){
        return discoveryClient.getServices();
    }
    
    @HystrixCommand(fallbackMethod = "fallbackUser")
    @GetMapping("/provider/null")
    public User getFallbackUser(){
        throw new RuntimeException();
    }


    public User fallbackUser(){
        return new User("fallback","fallback","fallback");
    }

}