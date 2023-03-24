package com.ibmmqbasics.topiclistener;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jms.annotation.EnableJms;

@SpringBootApplication
@EnableJms
public class TopicListenerApplication {

	public static void main(String[] args) {
		SpringApplication.run(TopicListenerApplication.class, args);
	}

}
