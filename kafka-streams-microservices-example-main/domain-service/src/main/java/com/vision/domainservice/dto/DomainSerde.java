package com.vision.domainservice.dto;

import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.serialization.Serializer;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.kafka.support.serializer.JsonSerializer;

import com.vision.domaincrawler.domain.Domain;

public class DomainSerde extends Serdes.WrapperSerde<Domain>{
	
	 public DomainSerde() {
	    super(new JsonSerializer<>(), new JsonDeserializer<>(Domain.class));
	 }
	
	
	public DomainSerde(Serializer<Domain> serializer, Deserializer<Domain> deserializer) {
		super(serializer, deserializer);
		// TODO Auto-generated constructor stub
	}

}
