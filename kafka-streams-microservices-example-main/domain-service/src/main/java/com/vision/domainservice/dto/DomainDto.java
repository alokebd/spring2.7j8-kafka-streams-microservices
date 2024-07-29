package com.vision.domainservice.dto;

import java.io.Serializable;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class DomainDto implements Serializable{
	private String domain;
	private String create_date;
	private String update_date;
	private String country;
	private boolean isDead;
	private String A;
	private String NS;
	private String CNAME;
	private String MX;
}
