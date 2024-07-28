package com.vision.domainservice.config;

import org.apache.kafka.streams.kstream.KStream;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.vision.domaincrawler.domain.Domain;

import java.util.function.Consumer;

@Configuration
public class DomainKafkaConsumer {

  //Java Consumer is used to consume domain 
  @Bean
  public Consumer<KStream<String, Domain>> domainService() {
    return kstream -> kstream.foreach((key, domain) -> {
      System.out.println(String.format("Domain consumed[%s] Status[%s]", domain.getDomain(), domain.isDead()));
    });
  }
}
