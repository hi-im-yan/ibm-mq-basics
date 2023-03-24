package com.ibmmqbasics.errorqueuelistener.converters;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ibmmqbasics.errorqueuelistener.model.ErrorMessage;
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
        throw new NotImplementedException();
    }

    @Override
    public Object fromMessage(Message message) throws JMSException, MessageConversionException {
        TextMessage textMessage = (TextMessage) message;
        String payload = textMessage.getText();
        log.info("inbound json='{}'", payload);

        ErrorMessage errorMessage = null;

        try {
            errorMessage = mapper.readValue(payload, ErrorMessage.class);
        } catch (Exception e) {
            log.error("ERROR CONVERTING TO ErrorMessage");
        }

        return errorMessage;
    }
}
