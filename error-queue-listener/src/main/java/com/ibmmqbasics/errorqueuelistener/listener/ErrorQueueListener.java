package com.ibmmqbasics.errorqueuelistener.listener;

import com.ibmmqbasics.errorqueuelistener.model.ErrorMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;


@Component
@Slf4j
public class ErrorQueueListener {

    @JmsListener(destination = "ERROR_QUEUE", containerFactory = "errorQueueListenerContainerFactory")
    public void processMessage(ErrorMessage message) {

        log.info("ERROR QUEUE LISTENER: " + message);

        // send notification on slack or something

        log.info("MESSAGE PROCESSED WITH SUCCESS");
    }
}
