package com.ibmmqbasics.topiclistener.listener;

import com.ibmmqbasics.topiclistener.model.TopicMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class TopicListener {

    @JmsListener(destination = "topic/message", containerFactory = "topicListenerContainerFactory")
    public void receive(TopicMessage message) {
        log.info("TOPIC LISTENER: " + message);
    }
}
