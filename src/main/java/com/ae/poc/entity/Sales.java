package com.ae.poc.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Sales {

	@Id
	private String spIid;
	
	private String spName;
	
	private Long spMobile;
	
	private String spEmail;
	
}
