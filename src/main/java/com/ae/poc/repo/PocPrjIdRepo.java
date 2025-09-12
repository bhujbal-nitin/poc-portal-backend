package com.ae.poc.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ae.poc.entity.PocUsecase;

@Repository
public interface PocPrjIdRepo extends JpaRepository<PocUsecase, String> {
    // Changed to String to match pocId type
}