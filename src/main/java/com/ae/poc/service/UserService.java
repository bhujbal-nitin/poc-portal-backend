package com.ae.poc.service;

import java.util.Optional;

public interface UserService {

	Optional<String> findFullNameByUserName(String username);

}
