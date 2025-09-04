package com.ae.poc.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ae.poc.entity.PocDetails;

@Repository
public interface PocDetailsRepo extends JpaRepository<PocDetails, Integer>{

}
