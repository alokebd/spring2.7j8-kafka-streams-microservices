package com.vision.domainprocessor.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vision.domaincrawler.domain.Domain;
import com.vision.domainprocessor.dto.DomainDto;
import com.vision.domainprocessor.entity.WebDomainEntity;
import com.vision.domainprocessor.repository.WebDomainRepository;

@Service
public class DomainProcessorService {
	@Autowired
	private WebDomainRepository webDomainRepository;

	public void saveWebEntity(Domain domain) {
		// ObjectMapper objectMapper = new ObjectMapper();
		// WebDomainEntity entity = objectMapper.readValue(domain,
		// WebDomainEntity.class);
		System.out.println("DomainProcessorService.saveWebEntity - call -1");
		if (domain != null && domain.getCountry() != null) {
			System.out.println("DomainProcessorService.saveWebEntity - country:" + domain.getCountry());
			ModelMapper mapper = new ModelMapper();
			WebDomainEntity entity = mapper.map(domain, WebDomainEntity.class);
			webDomainRepository.save(entity);
		}
	}

	public List<DomainDto> findAllValidDomainWithCountry() {
		List<WebDomainEntity> entities = webDomainRepository.findAll();
		List<DomainDto> dtoList = new ArrayList<DomainDto>();
		if (entities != null) {
			dtoList = entities.stream().map(domainEntity -> new ModelMapper().map(domainEntity, DomainDto.class))
					.collect(Collectors.toList());
		}
		return dtoList;
	}
}
