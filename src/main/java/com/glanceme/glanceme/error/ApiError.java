package com.glanceme.glanceme.error;

import java.util.Date;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude( value = Include.NON_NULL )
public class ApiError {

	private long timestamp = new Date().getTime();
	
	private String message;
	
	private int status ;
	
	private String url;
	
	private Map<String,String> validationErrors;
	
	
	
	public Map<String, String> getValidationErrors() {
		return validationErrors;
	}



	public void setValidationErrors(Map<String, String> validationErrors) {
		this.validationErrors = validationErrors;
	}



	public ApiError() {
		
	}
	
	

	public ApiError(String message, int status, String url) {
		this.message = message;
		this.status = status;
		this.url = url;
	}



	public long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
	
}
