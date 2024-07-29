package com.vision.domainservice.consumer;

import org.apache.kafka.clients.consumer.ConsumerRecord;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vision.domainservice.entity.DomainEntity;
import com.vision.domainservice.service.DomainConsumerService;

@Component
public class DomainConsumer {
	@Autowired
	private DomainConsumerService domainConsumerService;
	
	//@KafkaListener(topics = "web-domains", groupId = "domainService-applicationId")
	@KafkaListener(topics = "web-domains")
	public void listener(ConsumerRecord<String, String> consumerRecord) throws JsonProcessingException{
		 //System.out.println("Received domain key:" + consumerRecord.key());
	     //System.out.println("Received domain value: "+ consumerRecord.value());
	     ObjectMapper objectMapper = new ObjectMapper();
	     DomainEntity domainEntity = objectMapper.readValue(consumerRecord.value(), DomainEntity.class);
	     domainConsumerService.process(domainEntity);
	}
	

}
