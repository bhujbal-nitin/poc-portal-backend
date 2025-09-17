package com.ae.poc.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ae.poc.repo.UserRepository;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserRepository userRepo;

	@Override
	public Optional<String> findFullNameByUserName(String username) {
		// TODO Auto-generated method stub
		Optional<String> createdBy = this.userRepo.findFullNameByUserName(username);
		return createdBy;
	}

	@Override
	public List<String> getAllAssignTo() {
		// TODO Auto-generated method stub
		List<String> assigneToUser = this.userRepo.getAllAssignTo();
		return assigneToUser;
	}

}
