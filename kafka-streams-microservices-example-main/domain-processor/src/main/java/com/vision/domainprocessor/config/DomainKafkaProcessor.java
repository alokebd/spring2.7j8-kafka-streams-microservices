package com.vision.domainprocessor.config;

import org.apache.kafka.streams.kstream.KStream;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.vision.domaincrawler.domain.Domain;

import java.util.function.Function;

@Configuration
public class DomainKafkaProcessor {
  //Hear Function is created to filter data from KSteam (as Consumer) - we can publish it when requires 
  @Bean
  public Function<KStream<String, Domain>, KStream<String, Domain>> domainProcessor() {

    return kstream -> kstream.filter((key, domain) -> {
      if (domain.isDead()) {
        System.out.println("Inactive Domain: " + domain.getDomain());
      } else {
        System.out.println("Active Domain: " + domain.getDomain());
      }
      return !domain.isDead();
    });

  }
}
