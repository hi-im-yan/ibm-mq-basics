package com.ibmmqbasics.topicqueuesender.controller;

import com.ibmmqbasics.topicqueuesender.model.QueueMessage;
import com.ibmmqbasics.topicqueuesender.model.TopicMessage;
import com.ibmmqbasics.topicqueuesender.sender.Producer;
import com.ibmmqbasics.topicqueuesender.sender.Publisher;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

    private final Producer producer;
    private final Publisher publisher;

    public Controller(final Producer producer, final Publisher publisher) {
        this.producer = producer;
        this.publisher = publisher;
    }

    @PostMapping("/queue")
    public ResponseEntity<String> sendToQueue(@RequestBody QueueMessage message) {
        this.producer.createMessage(message);

        return ResponseEntity.ok("Message sent to the broker queue.");
    }

    @PostMapping("/topic")
    public ResponseEntity<String> sendToTopic(@RequestBody TopicMessage message) {
        this.publisher.createMessage(message);

        return ResponseEntity.ok("Message sent to the broker topic.");
    }
}
