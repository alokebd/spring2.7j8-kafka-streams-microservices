package com.vision.domainservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vision.domainservice.entity.DomainEntity;

@Repository
public interface DomainRepository extends JpaRepository<DomainEntity, Integer>{

}
