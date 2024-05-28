package com.microservices.demo.twitter.to.kafka.service.listner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import twitter4j.Status;
import twitter4j.StatusAdapter;

@Component
public class TwitterKafkaStatusListner extends StatusAdapter{
	
	private static final Logger log = LoggerFactory.getLogger(TwitterKafkaStatusListner.class);
	
	@Override
	public void onStatus(Status status) {
		log.info("Twitter status with text {}" + status.getText());
	}
}
