package com.ibmmqbasics.topicqueuelistener.converters;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ibmmqbasics.topicqueuelistener.model.ErrorMessage;
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
public class ErrorMessageConverter implements MessageConverter {

    private final ObjectMapper mapper;

    public ErrorMessageConverter() {
        this.mapper = new ObjectMapper();
    }

    @Override
    public Message toMessage(Object object, Session session) throws JMSException, MessageConversionException {
        ErrorMessage topicMessage = (ErrorMessage) object;
        String payload = null;

        try {
            payload = mapper.writeValueAsString(topicMessage);
            log.info("outbound json='{}'", payload);
        } catch (JsonProcessingException e) {
            log.error("ERROR CONVERTING TO ErrorMessage", e);
        }

        TextMessage message = session.createTextMessage(payload);

        return message;
    }

    @Override
    public Object fromMessage(Message message) throws JMSException, MessageConversionException {
        throw new NotImplementedException();
    }
}
