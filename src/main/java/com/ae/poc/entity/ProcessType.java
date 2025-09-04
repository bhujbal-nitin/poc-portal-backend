package com.ae.poc.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class ProcessType {
	
	@Id
	private String pId;
	
	private String pType;

}
