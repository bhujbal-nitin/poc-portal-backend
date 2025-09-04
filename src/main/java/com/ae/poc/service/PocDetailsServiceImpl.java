package com.ae.poc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ae.poc.entity.PocDetails;
import com.ae.poc.repo.PocDetailsRepo;

@Service
public class PocDetailsServiceImpl implements PocDetailsService{

	@Autowired
	private PocDetailsRepo pocDetailsRepo;

//	@Override
//	public PocDetails savePoc(PocDetails poc) {
//		// TODO Auto-generated method stub
//		PocDetails pocDetails = this.pocDetailsRepo.save(poc);
//		return pocDetails;
//	}
	
	@Override
	public PocDetails savePoc(PocDetails poc) {
	    PocDetails saved = pocDetailsRepo.save(poc);
	    System.out.println("Saved POC: " + saved); // log to verify ID
	    return saved;
	}


	
}

