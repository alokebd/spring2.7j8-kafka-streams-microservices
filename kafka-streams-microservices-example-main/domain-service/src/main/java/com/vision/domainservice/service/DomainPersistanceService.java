package com.vision.domainservice.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.vision.domainservice.dto.DomainDto;
import com.vision.domainservice.entity.DomainEntity;
import com.vision.domainservice.repository.DomainRepository;

@Service
public class DomainPersistanceService {
    @Autowired
	private DomainRepository domainRepository;
    
    
    public void save(DomainEntity entity) {
    	this.domainRepository.save(entity);
    }
    
    public List<DomainDto> findAll(){
    	List<DomainEntity> entities = domainRepository.findAll();
    	List<DomainDto> dtoList = new ArrayList<DomainDto>();
    	if (entities !=null) {
    		dtoList= entities.stream()
				.map(domainEntity -> new ModelMapper().map(domainEntity, DomainDto.class))
				.collect(Collectors.toList());
    	}
		return dtoList;
    }
    
    
}
