package com.ae.poc.dto;

import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class PocUsecaseDTO {
    private String pocId;
    private String pocName;
    private String entityType;
    private String entityName;
    private String salesPerson;
    private String description;
    private String assignedTo;
    private String createdBy;
    private LocalDate startDate;
    private LocalDate endDate;
    private LocalDate actualStartDate;
    private LocalDate actualEndDate;
    private BigDecimal estimatedEfforts;
    private BigDecimal totalEfforts;
    private BigDecimal varianceDays;
    private String approvedBy;
    private String remark;
    private String region;
    private Boolean isBillable;
    private String pocType;
    private String spocEmail;
    private String spocDesignation;
    private String tags;
    private String status;
}
