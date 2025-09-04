package com.ae.poc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ae.poc.repo.EndCustomerTypeRepo;

@Service
public class EndCustomerTypeServiceImpl implements EndCustomerTypeService{
	
	@Autowired
	private EndCustomerTypeRepo endCustomerTypeRepo;

	@Override
	public List<String> getAllCustomerTypes() {
		// TODO Auto-generated method stub
		List<String> customerTypes = this.endCustomerTypeRepo.getAllCustomerTypes();
		return customerTypes;
	}

}
