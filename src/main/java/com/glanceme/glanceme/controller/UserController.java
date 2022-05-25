package com.glanceme.glanceme.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.glanceme.glanceme.error.ApiError;
import com.glanceme.glanceme.service.UserService;
import com.glanceme.glanceme.shared.GenericResponse;
import com.glanceme.glanceme.user.User;

@RestController
public class UserController {

	@Autowired 
	UserService userService;
	
	@PostMapping("/api/1.0/users")
	GenericResponse createUser(@Valid @RequestBody User user) {
		
		
//		GenericResponse gr = new GenericResponse();
		userService.save(user);
//		gr.setMessage("User saved successfully.");
//		return gr;

		//alternative way for above commented code 
		return new GenericResponse("User saved succesfully.");
	}
	
	
	//the code below is used solely to handle error, in case error occurs , we customize the response acc
	//to our need using the following code
	@ExceptionHandler({MethodArgumentNotValidException.class})
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	ApiError handleValidationException(MethodArgumentNotValidException exception, HttpServletRequest request) {
		
		ApiError apiError = new ApiError("Validation Error..",400,request.getServletPath());
		
		BindingResult result = exception.getBindingResult();
//		
		Map<String, String> validationErrors = new HashMap<>();
//		
		for(FieldError fieldError : result.getFieldErrors()) {
//			
			validationErrors.put(fieldError.getField(), fieldError.getDefaultMessage());
		}
//		
		apiError.setValidationErrors(validationErrors);
//		
		return apiError;
	}
}
