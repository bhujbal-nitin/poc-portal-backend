package com.ae.poc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ae.poc.entity.PocDetails;
import com.ae.poc.service.EmailService;
import com.ae.poc.service.PocDetailsService;

@RestController
@RequestMapping("/poc")
@CrossOrigin(origins = "http://localhost:5173") 
public class PocDetailsController {
	
	@Autowired
	private PocDetailsService pocDetailsService;
	
	@Autowired
	private EmailService emailService;
	
//	@PostMapping("/savePoc")
//	public ResponseEntity<?> savePoc(@RequestBody PocDetails poc){
//		PocDetails pocDetails = this.pocDetailsService.savePoc(poc);
//		return new ResponseEntity<>(pocDetails, HttpStatus.OK);
//	}
	
	@PostMapping("/save")
	public ResponseEntity<?> savePoc(@RequestBody PocDetails poc) {
		
	    try {
	        PocDetails saved = pocDetailsService.savePoc(poc);
	        

	        if (saved.getPocId() != null) {
	            // 📧 Send email after saving
	            emailService.sendPocMail(saved, "devopsbyzielotech@gmail.com");

	            return ResponseEntity
	                    .status(HttpStatus.CREATED)
	                    .body(saved);
	        } else {
	            return ResponseEntity
	                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
	                    .body("POC creation failed: no ID generated.");
	        }
	    } catch (Exception e) {
	        return ResponseEntity
	                .status(HttpStatus.INTERNAL_SERVER_ERROR)
	                .body("POC creation failed: " + e.getMessage());
	    }
	}




}
