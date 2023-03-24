package com.ibmmqbasics.topiclistener.config;

import com.ibmmqbasics.topiclistener.converters.TopicMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;

import javax.jms.ConnectionFactory;

@Configuration
public class ListenerConfig {

    private final TopicMessageConverter topicMessageConverter;
    private final ConnectionFactory connectionFactory;

    public ListenerConfig(final TopicMessageConverter topicMessageConverter, final ConnectionFactory connectionFactory) {
        this.topicMessageConverter = topicMessageConverter;
        this.connectionFactory = connectionFactory;
    }

    // create multiple beans if you want to listen to multiple topics on the same application
    @Bean
    public DefaultJmsListenerContainerFactory topicListenerContainerFactory(ConnectionFactory connectionFactory) {
        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        factory.setPubSubDomain(true); // this will configure the listener to listen to topics
        factory.setMessageConverter(this.topicMessageConverter);
        return factory;
    }
}
