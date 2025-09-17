package com.ae.poc.service;

import java.util.List;

import com.ae.poc.entity.PocUsecase;

public interface PocPrjIdService {
    PocUsecase savePoc(PocUsecase pocUsecase);

	List<PocUsecase> getAllPoc();

	boolean deletePocById(String pocId);
	
	PocUsecase updatePocUsecase(String pocId, PocUsecase pocUsecase);

	boolean existsByPocId(String pocId);


}