package com.glanceme.glanceme.controller;

import java.util.Collections;
import java.util.Map;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;
import com.glanceme.glanceme.shared.CurrentUser;
import com.glanceme.glanceme.user.User;
import com.glanceme.glanceme.user.Views;

@RestController
public class LoginController {

	@PostMapping("/api/1.0/login")
	@JsonView(Views.Base.class)
	User loginHandler(@CurrentUser User loggedInUser) {
//	Map<String, Object> loginHandler(@CurrentUser User loggedInUser) {

		
		//		User loggedInUser = (User) authentication.getPrincipal();
		//Instead of above we used custom made annotation in the method parameter, to use above we need to 
		//pass method parameter called Authentication's instance
		
//		return Collections.singletonMap("id", loggedInUser.getId());
		return loggedInUser;

	}
}
