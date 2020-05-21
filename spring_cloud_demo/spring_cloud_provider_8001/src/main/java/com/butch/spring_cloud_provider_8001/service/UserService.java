package com.butch.spring_cloud_provider_8001.service;

import com.butch.spring_cloud_api.pojo.User;

import org.springframework.stereotype.Service;

@Service
public class UserService {
    public User getUser(){
        return new User("8001","8001","8001");
    }
}