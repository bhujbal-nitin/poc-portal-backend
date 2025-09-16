package com.ae.poc.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ae.poc.entity.Sales;

@Repository
public interface SalesRepo extends JpaRepository<Sales, Integer>{

	@Query("SELECT u.fullName FROM Sales s JOIN User u ON s.userId = u.id")
	List<String> getAllSalesPerson();

}
