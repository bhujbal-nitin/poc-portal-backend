package com.ae.poc.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;


@Entity
@Data
@Getter
@Setter
public class PocDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer pocId;


    @JsonProperty("salesPerson")   // ✅ matches frontend payload
    private String spName;

    @JsonProperty("region")
    private String region;

    @JsonProperty("endCustomerType")
    private String endCustomerType;

    @JsonProperty("processType")
    private String processType;

    @JsonProperty("companyName")
    private String companyName;

    @JsonProperty("spoc")
    private String spoc;

    @JsonProperty("spocEmail")
    private String spocEmail;

    @JsonProperty("designation")
    private String designation;

    @JsonProperty("mobileNumber")
    private String mobileNumber;

    @JsonProperty("usecase")
    private String usecase;

    @JsonProperty("brief")
    private String brief;

    // ✅ Partner fields
    @JsonProperty("partnerCompanyName")
    private String partnerCompanyName;

    @JsonProperty("partnerSpoc")
    private String partnerSpoc;

    @JsonProperty("partnerSpocEmail")
    private String partnerSpocEmail;

    @JsonProperty("partnerDesignation")
    private String partnerDesignation;

    @JsonProperty("partnerMobileNumber")
    private String partnerMobileNumber;
    
    public Integer getPocId() {
        return pocId;
    }

	public Object getSpName() {
		// TODO Auto-generated method stub
		return null;
	}

	public Object getRegion() {
		// TODO Auto-generated method stub
		return null;
	}

	public Object getCompanyName() {
		// TODO Auto-generated method stub
		return null;
	}

	public Object getPartnerCompanyName() {
		// TODO Auto-generated method stub
		return null;
	}

	public Object getUsecase() {
		// TODO Auto-generated method stub
		return null;
	}

	public Object getBrief() {
		// TODO Auto-generated method stub
		return null;
	}

	public Object getEndCustomerType() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getPartnerSpoc() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getPartnerSpocEmail() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getPartnerDesignation() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getProcessType() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getSpoc() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getSpocEmail() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getDesignation() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getMobileNumber() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getPartnerMobileNumber() {
		// TODO Auto-generated method stub
		return null;
	}
}
