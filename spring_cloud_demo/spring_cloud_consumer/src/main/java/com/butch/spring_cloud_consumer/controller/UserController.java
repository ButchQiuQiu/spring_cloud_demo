package com.butch.spring_cloud_consumer.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.butch.spring_cloud_api.pojo.User;
import com.butch.spring_cloud_consumer.mapper.UserMapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
public class UserController {
    @Autowired
    private RestTemplate restTemplate;
    private static final String REST_URL_PREFIX="http://DEMO";
    @GetMapping("/consumer/user")
    public String getUser() throws RestClientException, JsonProcessingException{
        ObjectMapper objectMapper = new ObjectMapper();
        
        return objectMapper.writeValueAsString(restTemplate.getForObject(REST_URL_PREFIX+"/provider/user",User.class));
    }

    @Autowired
    private UserMapper userMapper;
    @GetMapping("/consumer/feign")
    public User feign(){
        return userMapper.getUser();
    }
}