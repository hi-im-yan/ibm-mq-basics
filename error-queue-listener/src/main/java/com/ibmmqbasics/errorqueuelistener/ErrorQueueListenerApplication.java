package com.ibmmqbasics.errorqueuelistener;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jms.annotation.EnableJms;

@SpringBootApplication
@EnableJms
public class ErrorQueueListenerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ErrorQueueListenerApplication.class, args);
	}

}
