package com.ae.poc.service;

import java.util.List;
import java.util.Optional;

public interface UserService {

	Optional<String> findFullNameByUserName(String username);

	List<String> getAllAssignTo();

	List<String> getAllApprovedBy();

}
