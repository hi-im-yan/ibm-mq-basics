package com.ibmmqbasics.topicqueuesender.sender;

import com.ibmmqbasics.topicqueuesender.converters.QueueMessageConverter;
import com.ibmmqbasics.topicqueuesender.model.QueueMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class Producer {

    private final JmsTemplate jmsTemplate;
    private final QueueMessageConverter queueMessageConverter;

    private static final String queueName = "CUSTOM_QUEUE";

    public Producer(final JmsTemplate jmsTemplate, final QueueMessageConverter queueMessageConverter) {
        this.jmsTemplate = jmsTemplate;
        this.queueMessageConverter = queueMessageConverter;
    }

    public void createMessage(QueueMessage message) {
        log.info("PRODUCER " + message);

        jmsTemplate.setMessageConverter(queueMessageConverter);
        jmsTemplate.convertAndSend(queueName, message);
    }
}
