package com.ae.poc.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ae.poc.entity.EndCustomerType;

@Repository
public interface EndCustomerTypeRepo extends JpaRepository<EndCustomerType, String>{

	@Query("Select e.cType from EndCustomerType e")
	List<String> getAllCustomerTypes();

}
