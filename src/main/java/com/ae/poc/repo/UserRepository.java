package com.ae.poc.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ae.poc.entity.User;

public interface UserRepository extends JpaRepository<User, String> {
    Optional<User> findByUsername(String username);
    Optional<User> findByEmail(String email);
    
    @Query("SELECT u.fullName FROM User u WHERE u.username = :username")
	Optional<String> findFullNameByUserName(String username);
    
    @Query(value = "SELECT u.full_name FROM assigned_to a JOIN users u ON a.id = u.id", nativeQuery = true)
    List<String> getAllAssignTo();
    
    @Query(value = "SELECT u.full_name FROM approved_by a JOIN users u ON a.id = u.id", nativeQuery = true)
	List<String> getAllApprovedBy();

	
	
	
}
