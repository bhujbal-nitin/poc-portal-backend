package com.ae.poc.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ae.poc.entity.PocDetails;

@Repository
public interface PocDetailsRepo extends JpaRepository<PocDetails, Integer>{

	Optional<PocDetails> findById(Long pocId);
	
	 @Query(value = "SELECT * FROM poc_details WHERE id = :id", nativeQuery = true)
	 Optional<PocDetails> getPocById(@Param("id") Integer id);

//	@Query("SELECT p FROM PocDetails p WHERE p.id = :id")
//	Optional<PocDetails> getPocById(@Param("id") Integer id);

}
