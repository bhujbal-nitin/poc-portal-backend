package com.ae.poc.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Data
@Table(name = "poc_usecases")
public class PocUsecase {

    @Id
    @Column(name = "id", nullable = false, length = 100)
    private String pocId; // This matches the frontend "pocId"

    @Column(name = "use_case", nullable = false, length = 200)
    private String pocName; // Matches frontend "pocName"

    @Column(name = "type_1", nullable = false, length = 50)
    private String entityType; // Matches frontend "entityType"

    @Column(name = "name_of_company", nullable = false, length = 200)
    private String entityName; // Matches frontend "entityName"

    @Column(name = "sales_person", nullable = false, length = 100)
    private String salesPerson; // Matches frontend "salesPerson"

    @Column(name = "brief_description", length = 1000)
    private String description; // Matches frontend "description"

    @Column(name = "assigned_to", nullable = false, length = 100)
    private String assignedTo; // Matches frontend "assignedTo"

    @Column(name = "created_by", nullable = false, length = 100)
    private String createdBy; // Matches frontend "createdBy"

    @Column(name = "start_date", nullable = false)
    private LocalDate startDate; // Matches frontend "startDate"

    @Column(name = "end_date", nullable = false)
    private LocalDate endDate; // Matches frontend "endDate"

    @Column(name = "remark", length = 500)
    private String remark; // Matches frontend "remark"

    @Column(name = "region", nullable = false, length = 100)
    private String region; // Matches frontend "region"

    @Column(name = "is_billable", nullable = false)
    private Boolean isBillable; // Changed to Boolean to match frontend boolean

    @Column(name = "type_2", nullable = false, length = 50)
    private String pocType; // Matches frontend "pocType"

    @Column(name = "spoc_email", length = 150)
    private String spocEmail; // Matches frontend "spocEmail"

    @Column(name = "spoc_designation", length = 100)
    private String spocDesignation; // Matches frontend "spocDesignation"

    @Column(name = "tag", length = 500)
    private String tags; // Matches frontend "tags"

    // Optional fields with default values
    @Column(name = "actual_sdate")
    private LocalDate actualStartDate;
    
    @Column(name = "actual_edate")
    private LocalDate actualEndDate;
    
    @Column(name = "estimated_efforts", precision = 10, scale = 2)
    private BigDecimal estimatedEfforts = BigDecimal.ZERO;
    
    @Column(name = "approved_by", length = 100)
    private String approvedBy = "Pending";
    
    @Column(name = "total_efforts", precision = 10, scale = 2)
    private BigDecimal totalEfforts = BigDecimal.ZERO;
    
    @Column(name = "variance_days", precision = 10, scale = 2)
    private BigDecimal varianceDays = BigDecimal.ZERO;
    
    @Column(name = "status", length = 50)
    private String status = "Draft";
}