package com.ae.poc.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ae.poc.entity.PocUsecase;
import com.ae.poc.repo.PocPrjIdRepo;

import jakarta.transaction.Transactional;

@Service
public class PocPrjIdServiceImpl implements PocPrjIdService {

    @Autowired
    private PocPrjIdRepo pocRepo;

//    @Override
//    public PocUsecase savePoc(PocUsecase pocUsecase) {
//        try {
//            // Set default values for optional fields if needed
//            if (pocUsecase.getStatus() == null) {
//                pocUsecase.setStatus("Draft");
//            }
//            
//            return this.pocRepo.save(pocUsecase);
//        } catch (Exception e) {
//            throw new RuntimeException("Error saving POC: " + e.getMessage(), e);
//        }
//    }
    
 // In your PocService.java
    @Override
    public PocUsecase savePoc(PocUsecase pocUsecase) {
        // If the ID already exists (contains a number), just save it
        if (pocUsecase.getPocId() != null && pocUsecase.getPocId().matches(".*-\\d+$")) {
            return pocRepo.save(pocUsecase);
        }
        
        // Generate new ID based on prefix
        String prefix = pocUsecase.getPocId();
        String nextId = generateNextId(prefix);
        pocUsecase.setPocId(nextId);
        
        return pocRepo.save(pocUsecase);
    }

    private String generateNextId(String prefix) {
        // Find the highest number for this prefix
        Optional<PocUsecase> lastPoc = pocRepo.findTopByPocIdStartingWithOrderByPocIdDesc(prefix + "-");
        
        int nextNumber = 1;
        if (lastPoc.isPresent()) {
            String lastId = lastPoc.get().getPocId();
            try {
                String numberPart = lastId.substring(prefix.length() + 1);
                nextNumber = Integer.parseInt(numberPart) + 1;
            } catch (NumberFormatException | StringIndexOutOfBoundsException e) {
                // If parsing fails, start from 1
                nextNumber = 1;
            }
        }
        
        return String.format("%s-%02d", prefix, nextNumber);
    }

	@Override
	public List<PocUsecase> getAllPoc() {
		// TODO Auto-generated method stub
		List<PocUsecase> poc = this.pocRepo.findAll();
				
		return poc;
	}

	@Override
    public PocUsecase updatePocUsecase(String pocId, PocUsecase pocUsecase) {
        PocUsecase existingPoc = this.pocRepo.findById(pocId)
                .orElseThrow(() -> new RuntimeException("POC not found with id: " + pocId));

        // Update all fields from the request
        existingPoc.setPocName(pocUsecase.getPocName());
        existingPoc.setEntityType(pocUsecase.getEntityType());
        existingPoc.setEntityName(pocUsecase.getEntityName());
        existingPoc.setSalesPerson(pocUsecase.getSalesPerson());
        existingPoc.setDescription(pocUsecase.getDescription());
        existingPoc.setAssignedTo(pocUsecase.getAssignedTo());
        existingPoc.setCreatedBy(pocUsecase.getCreatedBy());
        existingPoc.setStartDate(pocUsecase.getStartDate());
        existingPoc.setEndDate(pocUsecase.getEndDate());
        existingPoc.setActualStartDate(pocUsecase.getActualStartDate());
        existingPoc.setActualEndDate(pocUsecase.getActualEndDate());
        existingPoc.setEstimatedEfforts(pocUsecase.getEstimatedEfforts());
        existingPoc.setTotalEfforts(pocUsecase.getTotalEfforts());
        
        // Calculate variance days if both actual dates are provided
        if (pocUsecase.getActualStartDate() != null && pocUsecase.getActualEndDate() != null) {
            long variance = java.time.temporal.ChronoUnit.DAYS.between(
                pocUsecase.getActualStartDate(), pocUsecase.getActualEndDate()
            );
            existingPoc.setVarianceDays(BigDecimal.valueOf(variance));
        } else {
            existingPoc.setVarianceDays(pocUsecase.getVarianceDays());
        }
        
        existingPoc.setApprovedBy(pocUsecase.getApprovedBy());
        existingPoc.setRemark(pocUsecase.getRemark());
        existingPoc.setRegion(pocUsecase.getRegion());
        existingPoc.setIsBillable(pocUsecase.getIsBillable());
        existingPoc.setPocType(pocUsecase.getPocType());
        existingPoc.setSpocEmail(pocUsecase.getSpocEmail());
        existingPoc.setSpocDesignation(pocUsecase.getSpocDesignation());
        existingPoc.setTags(pocUsecase.getTags());
        existingPoc.setStatus(pocUsecase.getStatus());

        return pocRepo.save(existingPoc);
    }
	
	@Override
    public boolean existsByPocId(String pocId) {
        return this.pocRepo.existsById(pocId);
    }
	
	@Override
    @Transactional
    public boolean deletePocById(String pocId) {
        try {
            // Check if the record exists
            if (pocRepo.existsById(pocId)) {
                pocRepo.deleteById(pocId);
                return true;
            }
            return false;
        } catch (Exception e) {
            throw new RuntimeException("Error deleting POC with ID: " + pocId, e);
        }
    }


}