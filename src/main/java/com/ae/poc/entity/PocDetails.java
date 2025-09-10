package com.ae.poc.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;


@Entity
@Data
@Getter
@Setter
@Table(name = "poc_details")
public class PocDetails {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "sp_name") // Map to database column
    @JsonProperty("salesPerson")
    private String spName;

    @Column(name = "region")
    @JsonProperty("region")
    private String region;

    @Column(name = "end_customer_type")
    @JsonProperty("endCustomerType")
    private String endCustomerType;

    @Column(name = "process_type")
    @JsonProperty("processType")
    private String processType;

    @Column(name = "company_name")
    @JsonProperty("companyName")
    private String companyName;

    @Column(name = "spoc")
    @JsonProperty("spoc")
    private String spoc;

    @Column(name = "spoc_email")
    @JsonProperty("spocEmail")
    private String spocEmail;

    @Column(name = "designation")
    @JsonProperty("designation")
    private String designation;

    @Column(name = "mobile_number")
    @JsonProperty("mobileNumber")
    private String mobileNumber;

    @Column(name = "usecase")
    @JsonProperty("usecase")
    private String usecase;

    @Column(name = "brief")
    @JsonProperty("brief")
    private String brief;

    // Partner fields
    @Column(name = "partner_company_name")
    @JsonProperty("partnerCompanyName")
    private String partnerCompanyName;

    @Column(name = "partner_spoc")
    @JsonProperty("partnerSpoc")
    private String partnerSpoc;

    @Column(name = "partner_spoc_email")
    @JsonProperty("partnerSpocEmail")
    private String partnerSpocEmail;

    @Column(name = "partner_designation")
    @JsonProperty("partnerDesignation")
    private String partnerDesignation;

    @Column(name = "partner_mobile_number")
    @JsonProperty("partnerMobileNumber")
    private String partnerMobileNumber;
    
    
}
