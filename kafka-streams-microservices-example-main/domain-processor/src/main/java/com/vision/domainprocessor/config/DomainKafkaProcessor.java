package com.vision.domainprocessor.config;

import org.apache.kafka.streams.kstream.KStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;

import com.vision.domaincrawler.domain.Domain;
import com.vision.domainprocessor.service.DomainProcessorService;

import java.util.function.Function;

@Configuration
@EnableKafka
public class DomainKafkaProcessor {
  @Autowired
  private DomainProcessorService domainProcessorService;	
  
  @Bean
  public Function<KStream<String, Domain>, KStream<String, Domain>> domainProcessor() {
	  /*
	   * Expected: 
	    Active Domain: facebook-panel.com
		Active Domain: facebook-ma.com
		Active Domain: facebook-link.com
		Active Domain: facebook-domain-verificationqt0414auohiwu1awuhlt4gdhl902n1.com
		Active Domain: facebook-turvallisuus.com
		Active Domain: facebook-ads-test-logic-form.com
		Active Domain: facebook-all-in-one.com
		Active Domain: facebook-safe.com
		Active Domain: wix-leads-facebook-campaign2.com
		Active Domain: wix-leads-facebook-campaign.com
		Active Domain: facebook-guideliest.com
		Active Domain: facebook-domain-verification0pr9r511u23vstj6he9zujgp39d5ld.com
		Active Domain: facebook-bm888.com
		Active Domain: facebook-vvip.com
		Active Domain: www-facebook-com-profile-phpid.com
		...
		..
	   */
    return kstream -> kstream.filter((key, domain) -> {
      if (domain.isDead()) {
        System.out.println("DomainKafkaProcessor.domainProcessor()- Inactive Domain: " + domain.getDomain());
      } else {
        System.out.println("DomainKafkaProcessor.domainProcessor()- Active Domain: " + domain.getDomain());
        domainProcessorService.saveWebEntity(domain);
      }
      return !domain.isDead();
    });
  }
}
