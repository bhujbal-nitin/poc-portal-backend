package com.ae.poc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ae.poc.repo.ProcessTypeRepo;

@Service
public class ProcessTypeServiceImpl implements ProcessTypeService{

	@Autowired
	private ProcessTypeRepo processTypeRepo;

	@Override
	public List<String> getAllProcessType() {
		// TODO Auto-generated method stub
		List<String> processTypes = this.processTypeRepo.getAllProcessType();
		return processTypes;
	}
	
}
