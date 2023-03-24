package com.ibmmqbasics.errorqueuelistener.config;

import com.ibmmqbasics.errorqueuelistener.converters.ErrorMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;

import javax.jms.ConnectionFactory;

@Configuration
public class ListenerConfig {

    private final ErrorMessageConverter errorMessageConverter;
    private final ConnectionFactory connectionFactory;

    public ListenerConfig(final ErrorMessageConverter errorMessageConverter, final ConnectionFactory connectionFactory) {
        this.errorMessageConverter = errorMessageConverter;
        this.connectionFactory = connectionFactory;
    }

    // create multiple beans if you want to listen to multiple queues on the same application
    @Bean
    public JmsListenerContainerFactory<?> errorQueueListenerContainerFactory(ConnectionFactory connectionFactory) {
        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        factory.setMessageConverter(this.errorMessageConverter);
        return factory;
    }
}
