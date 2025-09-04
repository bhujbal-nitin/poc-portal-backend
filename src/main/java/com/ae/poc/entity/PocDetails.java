package com.ae.poc.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;


@Entity
@Data
public class PocDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer pocId;

//    @JsonProperty("salesPerson")
    private String spName;

    @JsonProperty("region")
    private String rName;

    @JsonProperty("processType")
    private String pType;

    @JsonProperty("endCustomerType")
    private String endCustomerType;

    @JsonProperty("companyName")
    private String companyName;

    @JsonProperty("spoc")
    private String spoc;

    @JsonProperty("spocManager")
    private String spocManager;

    @JsonProperty("degree")
    private String degree;

    @JsonProperty("mobileNumber")
    private String mobNumber;

    @JsonProperty("usecase")
    private String usecase;

    @JsonProperty("brief")
    private String brief;
    
    public Integer getPocId() {
        return pocId;
    }
}
