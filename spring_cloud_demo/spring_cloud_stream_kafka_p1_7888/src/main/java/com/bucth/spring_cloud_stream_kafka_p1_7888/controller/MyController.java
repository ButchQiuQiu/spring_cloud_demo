package com.bucth.spring_cloud_stream_kafka_p1_7888.controller;

import com.bucth.spring_cloud_stream_kafka_p1_7888.service.DefaultProducer;
import com.bucth.spring_cloud_stream_kafka_p1_7888.service.Myproducer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyController {
    /**
     * 系统代理并且装配标记管道的Service
     */
    // 默认的管道
    @Autowired()
    private DefaultProducer defaultProducer;
    // 自定义的管道
    @Autowired()
    private Myproducer myproducer;

    //test
    @GetMapping("/test")
    public String Test(String test){
        return test;
    }
    
    @GetMapping("/sendMessage/string")
    public String publishMessageString(String payload) {
        // 发送消息
        defaultProducer.getSource().output()
                .send(MessageBuilder.withPayload(payload).setHeader("type", "string").build());
        return "success";
    }

    @GetMapping("/sendMyMessage/string")
    public String publishMyMessageString(String payload) {
        // 朝自定义管道发送消息
        myproducer.getMySource().myOutput()
                .send(MessageBuilder.withPayload(payload).setHeader("type", "string").build());
        return "success";
    }
}