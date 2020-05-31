package com.bucth.spring_cloud_stream_kafka_c1_7886.service;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

public interface MySink {
	String INPUT = "my-in";
    @Input(INPUT)
    SubscribableChannel myInput();
}