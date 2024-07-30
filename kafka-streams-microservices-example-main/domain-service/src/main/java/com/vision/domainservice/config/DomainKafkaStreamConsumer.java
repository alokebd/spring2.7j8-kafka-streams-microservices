package com.vision.domainservice.config;

import static org.apache.kafka.streams.StreamsConfig.APPLICATION_ID_CONFIG;
import static org.apache.kafka.streams.StreamsConfig.BOOTSTRAP_SERVERS_CONFIG;
import static org.apache.kafka.streams.StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG;
import static org.apache.kafka.streams.StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.kstream.KStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaStreamsDefaultConfiguration;
import org.springframework.kafka.config.KafkaStreamsConfiguration;
import com.vision.domaincrawler.domain.Domain;
import com.vision.domainservice.dto.DomainSerde;
import com.vision.domainservice.service.DomainConsumerService;

@Configuration
@EnableKafka
public class DomainKafkaStreamConsumer {
    //TODO 
	@Autowired
	//private DomainConsumerService domainConsumerService;
	private final String KAFKA_TOPIC = "web-domains";
	
	
	@Bean(name = KafkaStreamsDefaultConfiguration.DEFAULT_STREAMS_CONFIG_BEAN_NAME)
	KafkaStreamsConfiguration kStreamsConfig() {
		Map<String, Object> props = new HashMap<>();
		props.put(APPLICATION_ID_CONFIG, "domainService");
		props.put(BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092");

		props.put(DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass().getName());
		props.put(DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.Double().getClass().getName());

		props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);

		props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");

		return new KafkaStreamsConfiguration(props);
	}
    
	
	@Bean
	Consumer<KStream<String, Domain>> domainService() {
		System.out.println("DomainKafkaStreamConsumer.domainService - Config<<<<<<<<<<<<call -1");
		return kstream -> kstream.foreach((key, domain) -> {
			System.out.println(String.format("Domain consumed[%s] Status[%s]", domain.getDomain(), domain.isDead()));
		});
	}
	

	@Bean
	KStream<String, Domain> kStream() {
		final StreamsBuilder streamsBuilder = new StreamsBuilder();
		// streamsBuilder.stream("streams-plaintext-input").to("streams-pipe-output");
		//ObjectMapper objectMapper = new ObjectMapper();
		// DomainEntity domainEntity = objectMapper.readValue(consumerRecord.value(),
		// DomainEntity.class);
		streamsBuilder.stream(KAFKA_TOPIC).foreach((key, domain) -> {
			System.out.println(">>>>>>>>>>>>>>>>>kStream - Config<<<<<<<<<<<<call 3");
			// System.out.println(String.format("Domain consumed[%s] Status[%s]",
			// domain.getDomain(), domain.isDead()));

			// domainConsumerService.process(domain);
		});
		// .mapValues(null)

		// System.out.println(">>>>>>>>>>>>>>>>>kStream - Config<<<<<<<<<<<<");
		StreamsBuilder builder = new StreamsBuilder();

		Serde<String> stringSerde = Serdes.String();
		// System.out.println(">>>>>>>>>>>>>>>>>stringSerde : "+stringSerde);

		KStream<String, Domain> kStream = builder.stream(KAFKA_TOPIC, Consumed.with(stringSerde, new DomainSerde()));
		// kStream.peek((key, value) -> System.out.println("Incoming record - key " +key
		// +" value " + value));
		kStream.foreach((key, domain) -> {
			System.out.println(">>>>>>>>domain<<<<<<<<<<<<<<:" + domain.getDomain());
		});
		// Process KStream
		//this.domainConsumerService.process(kStream);

		System.out.println(">>>>>>>>>>>>>>>>>kStream - Config end<<<<<<<<<<<<");
		return kStream;
	}

}
