package com.microservices.demo.twitter.to.kafka.service;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.microservices.demo.twitter.to.kafka.service.config.TwitterConfigForFiledInjection;
import com.microservices.demo.twitter.to.kafka.service.config.TwitterToKafkaServiceConfigData;
import com.microservices.demo.twitter.to.kafka.service.runner.StreamRunner;

@SpringBootApplication
public class TwitterToKafkaServiceApplication implements CommandLineRunner {
	private static final Logger log = LoggerFactory.getLogger(TwitterToKafkaServiceApplication.class);

	private final TwitterToKafkaServiceConfigData twitterToKafkaServiceConfigData;

	@Autowired
	private TwitterConfigForFiledInjection twitterUsingFieldInjection;
	
	private final StreamRunner streamRunner; 

	public TwitterToKafkaServiceApplication(TwitterToKafkaServiceConfigData configData , StreamRunner streamRunner ) {
		this.twitterToKafkaServiceConfigData = configData;
		this.streamRunner = streamRunner;
	}

	public static void main(String[] args) {
		SpringApplication.run(TwitterToKafkaServiceApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		log.info("App Starts....");
		log.info(Arrays.toString(twitterToKafkaServiceConfigData.getTwitterKeyword().toArray(new String[] {})));
		log.info(twitterToKafkaServiceConfigData.getWelcomeMessage());
		
//		// now accessing using field injection
//		log.info(Arrays.toString(twitterUsingFieldInjection.getTwitterkeyword().toArray(new String[] {})));
//		log.info(twitterUsingFieldInjection.getWelcomeMessage());
		
		streamRunner.start();
	}

}
