package com.ae.poc.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ae.poc.dto.PocUsecaseDTO;
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
    

    @PutMapping("/update/{pocId}")
    public ResponseEntity<?> updatePocUsecase(@PathVariable String pocId, @RequestBody PocUsecaseDTO pocUsecaseDTO) {
        try {
            // Check if POC exists
            if (!this.pocService.existsByPocId(pocId)) {
                Map<String, String> response = new HashMap<>();
                response.put("success", "false");
                response.put("message", "POC not found with id: " + pocId);
                return ResponseEntity.badRequest().body(response);
            }

            // Convert DTO to Entity
            PocUsecase pocUsecase = convertToEntity(pocUsecaseDTO);
            
            // Update the POC
            PocUsecase updatedPoc = pocService.updatePocUsecase(pocId, pocUsecase);
            
            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("message", "POC updated successfully");
            response.put("data", updatedPoc);
            
            return ResponseEntity.ok(response);
            
        } catch (RuntimeException e) {
            Map<String, String> response = new HashMap<>();
            response.put("success", "false");
            response.put("message", e.getMessage());
            return ResponseEntity.badRequest().body(response);
        } catch (Exception e) {
            Map<String, String> response = new HashMap<>();
            response.put("success", "false");
            response.put("message", "Error updating POC: " + e.getMessage());
            return ResponseEntity.internalServerError().body(response);
        }
    }

    private PocUsecase convertToEntity(PocUsecaseDTO dto) {
        PocUsecase entity = new PocUsecase();
        entity.setPocId(dto.getPocId());
        entity.setPocName(dto.getPocName());
        entity.setEntityType(dto.getEntityType());
        entity.setEntityName(dto.getEntityName());
        entity.setSalesPerson(dto.getSalesPerson());
        entity.setDescription(dto.getDescription());
        entity.setAssignedTo(dto.getAssignedTo());
        entity.setCreatedBy(dto.getCreatedBy());
        entity.setStartDate(dto.getStartDate());
        entity.setEndDate(dto.getEndDate());
        entity.setActualStartDate(dto.getActualStartDate());
        entity.setActualEndDate(dto.getActualEndDate());
        entity.setEstimatedEfforts(dto.getEstimatedEfforts());
        entity.setTotalEfforts(dto.getTotalEfforts());
        entity.setVarianceDays(dto.getVarianceDays());
        entity.setApprovedBy(dto.getApprovedBy());
        entity.setRemark(dto.getRemark());
        entity.setRegion(dto.getRegion());
        entity.setIsBillable(dto.getIsBillable());
        entity.setPocType(dto.getPocType());
        entity.setSpocEmail(dto.getSpocEmail());
        entity.setSpocDesignation(dto.getSpocDesignation());
        entity.setTags(dto.getTags());
        entity.setStatus(dto.getStatus());
        
        return entity;
    }

    
    
    @DeleteMapping("/delete/{pocId}")
    public ResponseEntity<?> deletePocById(@PathVariable("pocId") String id) {
        try {
            boolean isDeleted = this.pocService.deletePocById(id);
            
            if (isDeleted) {
                return ResponseEntity.ok().body(
                    Map.of("success", true, "message", "POC record deleted successfully")
                );
            } else {
                return ResponseEntity.status(404).body(
                    Map.of("success", false, "message", "POC record not found with ID: " + id)
                );
            }
        } catch (Exception e) {
            return ResponseEntity.status(500).body(
                Map.of("success", false, "message", "Error deleting POC record: " + e.getMessage())
            );
        }
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