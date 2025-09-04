package com.ae.poc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ae.poc.service.SalesService;

@RestController
@RequestMapping("/poc")
@CrossOrigin(origins = "http://localhost:5173") 
public class SalesController {
	
	@Autowired
	private SalesService salesService;
	
	@GetMapping("/getAllSalesPerson")
	public ResponseEntity<?> getAllSalesPerson(){
		List<String> salesPersons = this.salesService.getAllSalesPerson();
		return new ResponseEntity<>(salesPersons, HttpStatus.OK);
	}

}
