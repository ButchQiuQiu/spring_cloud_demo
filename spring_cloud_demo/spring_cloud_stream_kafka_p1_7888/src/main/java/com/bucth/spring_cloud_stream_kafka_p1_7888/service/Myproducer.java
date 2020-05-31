package com.bucth.spring_cloud_stream_kafka_p1_7888.service;

import org.springframework.cloud.stream.annotation.EnableBinding;


/**
 * 自定义的管道
 */
@EnableBinding(MySource.class)
public class Myproducer {
    private MySource mySource;

    public Myproducer(MySource mySource) {
        this.setMySource(mySource);
    }

    public MySource getMySource() {
        return mySource;
    }

    public void setMySource(MySource mySource) {
        this.mySource = mySource;
    }

}