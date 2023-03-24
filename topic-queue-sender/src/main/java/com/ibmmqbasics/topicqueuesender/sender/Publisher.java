package com.ibmmqbasics.topicqueuesender.sender;

import com.ibmmqbasics.topicqueuesender.converters.TopicMessageConverter;
import com.ibmmqbasics.topicqueuesender.model.TopicMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class Publisher {

    private final JmsTemplate jmsTemplate;
    private final TopicMessageConverter topicMessageConverter;

    private static final String topicName = "topic/message";

    public Publisher(final JmsTemplate jmsTemplate, final TopicMessageConverter topicMessageConverter) {
        this.jmsTemplate = jmsTemplate;
        this.topicMessageConverter = topicMessageConverter;
    }

    public void createMessage(TopicMessage message) {
        log.info("PUBLISHER " + message);

        jmsTemplate.setPubSubDomain(true); // this configuration makes it publish on a topic
        jmsTemplate.setMessageConverter(topicMessageConverter);
        jmsTemplate.convertAndSend(topicName, message);
    }
}
