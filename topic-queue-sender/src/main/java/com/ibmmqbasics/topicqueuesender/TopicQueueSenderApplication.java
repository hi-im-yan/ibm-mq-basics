package com.ibmmqbasics.topicqueuesender;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jms.annotation.EnableJms;

@SpringBootApplication
@EnableJms
public class TopicQueueSenderApplication {

	public static void main(String[] args) {
		SpringApplication.run(TopicQueueSenderApplication.class, args);
	}

}
