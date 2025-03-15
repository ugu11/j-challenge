package com.ugu.javachallenge.rest.config;

import com.ugu.javachallenge.rest.data.Calculation;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;
import org.springframework.kafka.listener.ContainerProperties;
import org.springframework.kafka.listener.KafkaMessageListenerContainer;
import org.springframework.kafka.requestreply.ReplyingKafkaTemplate;


@Configuration
public class KafkaConfig {
    private final KafkaConsumerConfig kafkaConsumerConfig = new KafkaConsumerConfig();
    private final KafkaProducerConfig kafkaProducerConfig = new KafkaProducerConfig();

//    @Value("${kafka.topic.requestreply-topic}")
    private final String REPLY_TOPIC = "reply-topic";

    @Bean
    public ReplyingKafkaTemplate<String, Calculation, Calculation> replyKafkaTemplate(ProducerFactory<String, Calculation> pf, KafkaMessageListenerContainer<String, Calculation> container){
        return new ReplyingKafkaTemplate<>(pf, container);

    }

    @Bean
    public KafkaMessageListenerContainer<String, Calculation> replyContainer(ConsumerFactory<String, Calculation> cf) {
        ContainerProperties containerProperties = new ContainerProperties(REPLY_TOPIC);
        return new KafkaMessageListenerContainer<>(cf, containerProperties);
    }

    @Bean
    public KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<String, Calculation>> kafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, Calculation> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(this.kafkaConsumerConfig.consumerFactory());
        factory.setReplyTemplate(this.kafkaProducerConfig.kafkaTemplate());
        return factory;
    }

}