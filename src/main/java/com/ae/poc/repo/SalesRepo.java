package com.ae.poc.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ae.poc.entity.Sales;

@Repository
public interface SalesRepo extends JpaRepository<Sales, String>{

	@Query("SELECT s.spName FROM Sales s")
	List<String> getAllSalesPerson();

}
