package com.glanceme.glanceme.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.glanceme.glanceme.repo.UserRepository;
import com.glanceme.glanceme.user.User;

@Service
public class UserService {

	UserRepository userRepository ;
	
	BCryptPasswordEncoder passwordEncoder;
	
	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
		this.passwordEncoder=new BCryptPasswordEncoder();
	}
	
	public User save(User user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		
		return userRepository.save(user);
	}
	
}


