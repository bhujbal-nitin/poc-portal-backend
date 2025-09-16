package com.ae.poc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ae.poc.entity.PocUsecase;
import com.ae.poc.repo.PocPrjIdRepo;

@Service
public class PocPrjIdServiceImpl implements PocPrjIdService {

    @Autowired
    private PocPrjIdRepo pocRepo;

    @Override
    public PocUsecase savePoc(PocUsecase pocUsecase) {
        try {
            // Set default values for optional fields if needed
            if (pocUsecase.getStatus() == null) {
                pocUsecase.setStatus("Draft");
            }
            
            return this.pocRepo.save(pocUsecase);
        } catch (Exception e) {
            throw new RuntimeException("Error saving POC: " + e.getMessage(), e);
        }
    }

	@Override
	public List<PocUsecase> getAllPoc() {
		// TODO Auto-generated method stub
		List<PocUsecase> poc = this.pocRepo.findAll();
				
		return poc;
	}


}