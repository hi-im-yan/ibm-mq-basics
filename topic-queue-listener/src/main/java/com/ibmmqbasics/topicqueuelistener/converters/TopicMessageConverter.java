package com.ibmmqbasics.topicqueuelistener.converters;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ibmmqbasics.topicqueuelistener.model.TopicMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.support.converter.MessageConversionException;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.stereotype.Component;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;

@Slf4j
@Component
public class TopicMessageConverter implements MessageConverter {

    private final ObjectMapper mapper;

    public TopicMessageConverter() {
        this.mapper = new ObjectMapper();
    }

    @Override
    public Message toMessage(Object object, Session session) throws JMSException, MessageConversionException {
        throw new NotImplementedException();
    }

    @Override
    public Object fromMessage(Message message) throws JMSException, MessageConversionException {
        TextMessage textMessage = (TextMessage) message;
        String payload = textMessage.getText();
        log.info("inbound json='{}'", payload);

        TopicMessage topicMessage = null;

        try {
            topicMessage = mapper.readValue(payload, TopicMessage.class);
        } catch (Exception e) {
            log.error("ERROR CONVERTING TO TopicMessage");
        }

        return topicMessage;
    }
}
