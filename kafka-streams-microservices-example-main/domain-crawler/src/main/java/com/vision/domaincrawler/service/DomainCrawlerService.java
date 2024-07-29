package com.vision.domaincrawler.service;

import org.springframework.http.MediaType;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.vision.domaincrawler.domain.Domain;
import com.vision.domaincrawler.utils.DomainList;

import reactor.core.publisher.Mono;

@Service
public class DomainCrawlerService {

	private KafkaTemplate<String, Domain> kafkaTemplate;
	private final String KAFKA_TOPIC = "web-domains"; // 1st Topic

	public DomainCrawlerService(KafkaTemplate<String, Domain> kafkaTemplate) {
		this.kafkaTemplate = kafkaTemplate;
	}

	public void crawl(String name) {

		Mono<DomainList> domainListMono = WebClient.create().get()
				.uri("https://api.domainsdb.info/v1/domains/search?domain=" + name + "&zone=com")
				.accept(MediaType.APPLICATION_JSON).retrieve().bodyToMono(DomainList.class);

		// to publish we need to subscribe
		domainListMono.subscribe(domainList -> {
			domainList.getDomains().forEach(domain -> {
				kafkaTemplate.send(KAFKA_TOPIC, domain);
				System.out.println("Domain message" + domain.getDomain());
			});
		});

	}
}
