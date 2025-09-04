package com.ae.poc.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ae.poc.entity.Region;

@Repository
public interface RegionRepo extends JpaRepository<Region, String>{

	@Query("Select r.rName from Region r")
	List<String> getAllRegion();

}
