package com.ibmmqbasics.topicqueuelistener.config;

import com.ibmmqbasics.topicqueuelistener.converters.QueueMessageConverter;
import com.ibmmqbasics.topicqueuelistener.converters.TopicMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;

import javax.jms.ConnectionFactory;

@Configuration
public class ListenerConfig {

    private final QueueMessageConverter queueMessageConverter;
    private final TopicMessageConverter topicMessageConverter;
    private final ConnectionFactory connectionFactory;

    public ListenerConfig(QueueMessageConverter queueMessageConverter, TopicMessageConverter topicMessageConverter, ConnectionFactory connectionFactory) {
        this.queueMessageConverter = queueMessageConverter;
        this.topicMessageConverter = topicMessageConverter;
        this.connectionFactory = connectionFactory;
    }

    // create multiple beans if you want to listen to multiple queues on the same application
    @Bean
    public JmsListenerContainerFactory<?> queueListenerContainerFactory(ConnectionFactory connectionFactory) {
        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        factory.setMessageConverter(this.queueMessageConverter);
        return factory;
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
