package com.ae.poc.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ae.poc.service.UserService;

@RestController
@RequestMapping("/poc")
@CrossOrigin(origins = "http://localhost:5173") 
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/getCreatedBy")
    public ResponseEntity<?> getCreatedBy(@RequestParam String username) {
        Optional<String> createdBy = userService.findFullNameByUserName(username);
        return new ResponseEntity<>(createdBy, HttpStatus.OK);
    }

}
