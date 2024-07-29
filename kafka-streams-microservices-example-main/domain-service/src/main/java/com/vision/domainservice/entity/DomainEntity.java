package com.vision.domainservice.entity;

import java.io.Serializable;
import javax.persistence.*;

import lombok.Data;

@Data
@Entity
@Table(name = "web_domains")
public class DomainEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String domain;
	private String create_date;
	private String update_date;
	private String country;
	private boolean isDead;
	private String A;
	private String NS;
	private String CNAME;
	private String MX;
	private String TXT;
}
