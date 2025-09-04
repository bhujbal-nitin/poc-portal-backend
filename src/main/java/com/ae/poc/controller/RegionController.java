package com.ae.poc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ae.poc.service.RegionService;

@RestController
@RequestMapping("/poc")
@CrossOrigin(origins = "http://localhost:5173") 
public class RegionController {
	
	@Autowired
	private RegionService regionService;
	
	@GetMapping("/getAllRegion")
	public ResponseEntity<?> getAllRegion(){
		List<String> regions = this.regionService.getAllRegion();
		return new ResponseEntity<>(regions, HttpStatus.OK);
	}

}
