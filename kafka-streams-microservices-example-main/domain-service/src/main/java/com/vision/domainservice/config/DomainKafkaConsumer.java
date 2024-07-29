package com.vision.domainservice.config;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.streams.kstream.KStream;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;

import com.vision.domaincrawler.domain.Domain;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

@Configuration
public class DomainKafkaConsumer {
	// Java Consumer is used to consume domain
	@Bean
	public Consumer<KStream<String, Domain>> domainService() {
		return kstream -> kstream.foreach((key, domain) -> {
			System.out.println(String.format("Domain consumed[%s] Status[%s]", domain.getDomain(), domain.isDead()));
		});
	}
    
	
    @Bean
    ConsumerFactory<String, String> consumerFactory() {
		Map<String, Object> props = new HashMap<>();
		props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092");
		
		props.put(ConsumerConfig.GROUP_ID_CONFIG, "domainService");
		//props.put(ConsumerConfig.GROUP_ID_CONFIG, "domainService-applicationId");
		props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "latest");
		return new DefaultKafkaConsumerFactory<>(props);
	}
    
    //Concurrent Kafka listener
	@Bean
	public ConcurrentKafkaListenerContainerFactory<String, String> kafkaListenerContainerFactory() {
		ConcurrentKafkaListenerContainerFactory<String, String> factory = new ConcurrentKafkaListenerContainerFactory<>();
		factory.setConsumerFactory(consumerFactory());
		factory.setConcurrency(3);
		return factory;
	}
}