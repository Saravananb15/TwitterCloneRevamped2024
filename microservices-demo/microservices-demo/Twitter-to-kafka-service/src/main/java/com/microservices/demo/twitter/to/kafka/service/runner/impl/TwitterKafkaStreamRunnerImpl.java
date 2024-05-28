package com.microservices.demo.twitter.to.kafka.service.runner.impl;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.microservices.demo.twitter.to.kafka.service.config.TwitterToKafkaServiceConfigData;
import com.microservices.demo.twitter.to.kafka.service.listner.TwitterKafkaStatusListner;
import com.microservices.demo.twitter.to.kafka.service.runner.StreamRunner;

import twitter4j.FilterQuery;
import twitter4j.TwitterException;
import twitter4j.TwitterStream;
import twitter4j.TwitterStreamFactory;

@Component
public class TwitterKafkaStreamRunnerImpl implements StreamRunner {
	private static final Logger log = LoggerFactory.getLogger(TwitterKafkaStreamRunnerImpl.class);
	private final TwitterKafkaStatusListner twitterKafkaStatusListner;
	private final TwitterToKafkaServiceConfigData twitterToKafkaServiceConfigData;
	private TwitterStream twitterStream;

	public TwitterKafkaStreamRunnerImpl(TwitterKafkaStatusListner configData,
			TwitterToKafkaServiceConfigData StatusListner) {
		super();
		this.twitterKafkaStatusListner = configData;
		this.twitterToKafkaServiceConfigData = StatusListner;
	}

	@Override
	public void start() throws TwitterException {
		// TODO Auto-generated method stub
		twitterStream = new TwitterStreamFactory().getInstance();
		twitterStream.addListener(twitterKafkaStatusListner);
		addFilter();
	}
	public void shutdown() {
		if(twitterStream != null) {
			log.info("Closing twitter stream");
			twitterStream.shutdown();
		}
	}
	private void addFilter() {
		String[] keywords = twitterToKafkaServiceConfigData.getTwitterKeyword().toArray(new String[0]);
		FilterQuery filterQuery = new FilterQuery(keywords);
		twitterStream.filter(filterQuery);
		log.info("Started filtering twitter stream for keyword {}", Arrays.toString(keywords));
	}

}
