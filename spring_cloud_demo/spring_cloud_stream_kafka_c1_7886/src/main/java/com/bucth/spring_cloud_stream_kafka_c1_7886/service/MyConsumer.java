package com.bucth.spring_cloud_stream_kafka_c1_7886.service;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;

@EnableBinding(MySink.class)
public class MyConsumer {
	private static final Logger logger = LoggerFactory.getLogger(MyConsumer.class);

	@StreamListener(target = MySink.INPUT)
	public void consume(String message) {
		logger.info("recieved a string message : " + message);
	}

	@StreamListener(target = MySink.INPUT, condition = "headers['type']=='chat'")
	public void handle(@Payload ChatMessage message) {
		final DateTimeFormatter df = DateTimeFormatter.ofLocalizedTime(FormatStyle.MEDIUM)
				.withZone(ZoneId.systemDefault());
		final String time = df.format(Instant.ofEpochMilli(message.getTime()));
		logger.info("recieved a complex message : [{}]: {}", time, message.getContents());
	}
}