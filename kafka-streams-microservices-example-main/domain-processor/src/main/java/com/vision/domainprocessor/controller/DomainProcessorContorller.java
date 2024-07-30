package com.vision.domainprocessor.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.vision.domainprocessor.dto.DomainDto;
import com.vision.domainprocessor.service.DomainProcessorService;

@RestController
@RequestMapping("/api/domains")
public class DomainProcessorContorller {
	
	@Autowired
	private DomainProcessorService domainProcessorService;
	
	@GetMapping("/v2")
	public ResponseEntity<List<DomainDto>> findAllDomainsValidCountyName(){
		List<DomainDto> dtoList = domainProcessorService.findAllValidDomainWithCountry();
		if (dtoList.size() != 0) {
			return new ResponseEntity<>(dtoList, HttpStatus.OK);
		}
		else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

}
