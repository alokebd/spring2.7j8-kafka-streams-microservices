package com.vision.domainservice.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vision.domainservice.dto.DomainDto;
import com.vision.domainservice.service.DomainPersistanceService;

@RestController
@RequestMapping("/api/domains")
public class DomainController {
	
	private DomainPersistanceService domainPersistanceService;

	public DomainController(DomainPersistanceService domainPersistanceService) {
		this.domainPersistanceService = domainPersistanceService;
	}
	
	@GetMapping("/v1")
	public ResponseEntity<List<DomainDto>> findAllDomains(){
		List<DomainDto> dtoList = domainPersistanceService.findAll();
		if (dtoList.size() != 0) {
			return new ResponseEntity<>(dtoList, HttpStatus.OK);
		}
		else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

}
