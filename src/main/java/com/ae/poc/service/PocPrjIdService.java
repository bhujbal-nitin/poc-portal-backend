package com.ae.poc.service;

import java.util.List;

import com.ae.poc.entity.PocUsecase;

public interface PocPrjIdService {
    PocUsecase savePoc(PocUsecase pocUsecase);

	List<PocUsecase> getAllPoc();


}