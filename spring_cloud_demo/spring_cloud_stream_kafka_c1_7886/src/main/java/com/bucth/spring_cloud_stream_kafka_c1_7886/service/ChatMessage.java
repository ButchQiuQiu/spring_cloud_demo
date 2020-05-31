package com.bucth.spring_cloud_stream_kafka_c1_7886.service;
public class ChatMessage {

	private String contents;
	private long time;

	public ChatMessage() {

	}

	public ChatMessage(String contents, long time) {

		this.contents = contents;
		this.time = time;
	}

	public String getContents() {

		return contents;
	}

	public long getTime() {

		return time;
	}

	public void setTime(long time) {

		this.time = time;
	}

	@Override
	public String toString() {

		return "ChatMessage [contents=" + contents + ", time=" + time + "]";
	}

}