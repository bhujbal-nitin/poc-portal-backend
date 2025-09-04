package com.ae.poc.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class EndCustomerType {

	@Id
	private String cId;
	
	private String cType;
	
}
