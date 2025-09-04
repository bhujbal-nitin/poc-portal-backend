package com.ae.poc.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Region {
	
	@Id
	private String rId;
	
	private String rName;

}
