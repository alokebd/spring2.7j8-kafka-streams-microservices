package com.vision.domainprocessor.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vision.domainprocessor.entity.WebDomainEntity;

@Repository
public interface WebDomainRepository extends JpaRepository<WebDomainEntity, Integer>{

}
