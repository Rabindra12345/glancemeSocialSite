package com.glanceme.glanceme.user;

import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.glanceme.glanceme.repo.UserRepository;

public class UniqueUsernameValidator implements ConstraintValidator<UniqueUsername, String> {

	@Autowired
	UserRepository userRepository;
	
	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		
		//Using list of type string doesnot give desired output and it check the condition but 
		//if condition fails, it throws status code of 500 i.e. internal server error
			//List<String> username = userRepository.findByUsername(value);

		
		
		//either you can do this or you can do alternative approach like alter.2
			List<User> username = userRepository.findByUsername(value);
			//here comparing username with the null doesnot work 
			//if(username==null) {
			if(username.isEmpty()) {
				return true;
			}
		
		//alter. 2 
//		User inDB = userRepository.findByUsername(value);
//			if(inDB==null) {
//				return true;
//			}
		
		return false;
	}
	
}
