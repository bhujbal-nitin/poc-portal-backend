package com.ae.poc.repo;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import com.ae.poc.entity.PocUsecase;

@Repository
public interface PocPrjIdRepo extends JpaRepository<PocUsecase, String> {
	
	// In your PocRepository.java
	Optional<PocUsecase> findTopByPocIdStartingWithOrderByPocIdDesc(String prefix);


}