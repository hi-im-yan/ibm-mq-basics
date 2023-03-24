package com.ibmmqbasics.topicqueuelistener.sender;

import com.ibmmqbasics.topicqueuelistener.converters.ErrorMessageConverter;
import com.ibmmqbasics.topicqueuelistener.model.ErrorMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ErrorProducer {

    private final JmsTemplate jmsTemplate;
    private final ErrorMessageConverter errorMessageConverter;

    private static final String queueName = "ERROR_QUEUE";

    public ErrorProducer(final JmsTemplate jmsTemplate, final ErrorMessageConverter errorMessageConverter) {
        this.jmsTemplate = jmsTemplate;
        this.errorMessageConverter = errorMessageConverter;
    }

    public void createMessage(ErrorMessage message) {
        log.info("PRODUCER " + message);

        jmsTemplate.setMessageConverter(errorMessageConverter);
        jmsTemplate.convertAndSend(queueName, message);
    }
}
