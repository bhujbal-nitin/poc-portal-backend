package com.ae.poc.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ae.poc.entity.ProcessType;

@Repository
public interface ProcessTypeRepo extends JpaRepository<ProcessType, String>{

	@Query("Select p.pType from ProcessType p")
	List<String> getAllProcessType();

}
