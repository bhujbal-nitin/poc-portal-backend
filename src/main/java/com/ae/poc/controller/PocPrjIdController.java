package com.ae.poc.controller;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ae.poc.entity.PocUsecase;
import com.ae.poc.service.PocPrjIdService;

@RestController
@RequestMapping("/poc")
@CrossOrigin(origins = "http://localhost:5173") 
public class PocPrjIdController {
    
    private static final Logger logger = LoggerFactory.getLogger(PocPrjIdController.class);
    
    @Autowired
    private PocPrjIdService pocService;
    
    @GetMapping("/all")
    public ResponseEntity<?> getAllPoc(){
    	List<PocUsecase> poc = this.pocService.getAllPoc();
    	
    	return new ResponseEntity<>(poc, HttpStatus.OK);
    }
    

    
    @PostMapping("/savepocprjid")
    public ResponseEntity<?> savePoc(@RequestBody PocUsecase pocUsecase) {
        try {
            logger.info("Received POC data: {}", pocUsecase);
            
            // Validate required fields
            if (pocUsecase.getPocId() == null || pocUsecase.getPocId().isEmpty()) {
                logger.error("POC ID is required");
                return ResponseEntity.badRequest().body(Map.of(
                    "success", false,
                    "message", "POC ID is required"
                ));
            }
            
            PocUsecase savedPoc = pocService.savePoc(pocUsecase);
            logger.info("POC saved successfully: {}", savedPoc);
            
            // Return the expected format with success flag
            return ResponseEntity.ok(Map.of(
                "success", true,
                "message", "POC created successfully",
                "data", savedPoc
            ));
            
        } catch (Exception e) {
            logger.error("Error saving POC: ", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(Map.of(
                    "success", false,
                    "message", "Failed to create POC: " + e.getMessage()
                ));
        }
    }
}