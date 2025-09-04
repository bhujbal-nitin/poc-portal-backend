package com.ae.poc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ae.poc.service.ProcessTypeService;

@RestController
@RequestMapping("/poc")
@CrossOrigin(origins = "http://localhost:5173") 
public class ProcessTypeController {
	
	@Autowired
	private ProcessTypeService processTypeService;
	
	@GetMapping("/getAllProcessType")
	public ResponseEntity<?> getAllProcessType(){
		List<String> processTypes = this.processTypeService.getAllProcessType();
		return new ResponseEntity<>(processTypes, HttpStatus.OK);
	}

}
