package com.ibmmqbasics.topicqueuelistener.listener;

import com.ibmmqbasics.topicqueuelistener.model.ErrorMessage;
import com.ibmmqbasics.topicqueuelistener.model.QueueMessage;
import com.ibmmqbasics.topicqueuelistener.sender.ErrorProducer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import javax.validation.ConstraintViolation;
import java.util.Set;

@Component
@Slf4j
public class QueueListener {

    private final ErrorProducer errorProducer;
    private final LocalValidatorFactoryBean validator;

    public QueueListener(final ErrorProducer errorProducer, final LocalValidatorFactoryBean validator) {
        this.errorProducer = errorProducer;
        this.validator = validator;
    }

    @JmsListener(destination = "CUSTOM_QUEUE", containerFactory = "queueListenerContainerFactory")
    public void processMessage(QueueMessage message) {

        Set<ConstraintViolation<QueueMessage>> violations = validator.validate(message);
        log.info("QUEUE LISTENER: " + message);

        if (!violations.isEmpty()) {
            ErrorMessage errorMessage = new ErrorMessage();
            errorMessage.setOrigin("TOPIC-QUEUE-LISTENER");
            violations.forEach( violation -> {
                errorMessage.getErrors().add(violation.getMessage());
                log.error("VALIDATOR: " + violation.getMessage());
            });

            this.errorProducer.createMessage(errorMessage);
            return;
        }

        log.info("MESSAGE PROCESSED WITH SUCCESS");
    }
}
