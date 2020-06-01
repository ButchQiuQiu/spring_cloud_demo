package com.bucth.spring_cloud_stream_kafka_p1_7888.service;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

/**
 * 自定义管道类·································
 */
public interface MySource {


    String OUTPUT = "my-out";

    @Output(MySource.OUTPUT)    
    MessageChannel myOutput();

}