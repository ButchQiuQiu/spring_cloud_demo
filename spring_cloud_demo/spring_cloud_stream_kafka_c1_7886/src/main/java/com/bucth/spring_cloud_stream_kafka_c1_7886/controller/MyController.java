package com.bucth.spring_cloud_stream_kafka_c1_7886.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyController {
    @GetMapping("/getMessage/string")
    public String getMessage(){
        return "String";
    }
}