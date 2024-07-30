package com.vision.domainservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.vision.domainservice.entity.DomainEntity;

@Component
public class DomainConsumerService {
	@Autowired
	private DomainPersistanceService domainPersistanceService;

	public void process(DomainEntity employeeEntity) {

		domainPersistanceService.save(employeeEntity);
	}
}
