package com.bucth.spring_cloud_stream_kafka_p1_7888.service;

import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;

/**
 * 默认管道
 */
@EnableBinding(Source.class)
public class DefaultProducer {
    private Source source;

    public DefaultProducer(Source source) {
        this.source = source;
    }

    public Source getSource() {
        return source;
    }

    public void setSource(Source source) {
        this.source = source;
    }

}