package com.ae.poc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ae.poc.repo.SalesRepo;

@Service
public class SalesServiceImpl implements SalesService{

	
	@Autowired
	private SalesRepo salesRepo;

	@Override
	public List<String> getAllSalesPerson() {
		// TODO Auto-generated method stub
		List<String> salesPersons = this.salesRepo.getAllSalesPerson();
		return salesPersons;
	}
	
}
