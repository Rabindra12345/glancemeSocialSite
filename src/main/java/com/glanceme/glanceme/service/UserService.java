package com.glanceme.glanceme.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.glanceme.glanceme.repo.UserRepository;
import com.glanceme.glanceme.user.User;

@Service
public class UserService {

	UserRepository userRepository ;
	
	PasswordEncoder passwordEncoder;
	
	public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
		this.userRepository = userRepository;
		
//		this.passwordEncoder=new BCryptPasswordEncoder();
		this.passwordEncoder = passwordEncoder;
	}
	
	public User save(User user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		
		return userRepository.save(user);
	}
	
}


