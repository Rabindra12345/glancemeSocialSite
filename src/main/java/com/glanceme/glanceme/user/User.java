package com.glanceme.glanceme.user;

import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
//import javax.validation.constraints.NotNull;
//import javax.validation.constraints.Pattern;
//import javax.validation.constraints.Size;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

//@Data
@Entity
public class User {
	

	@Id
	@GeneratedValue
	private long id ;
//	@NotNull
	
	//NOTE: Here if we want to translate this english response message to another language
	//its always a good practice to extract the static field out of our code and 
//	@NotEmpty
//	(message="Username should not be empty")
//	@NotEmpty
	@UniqueUsername
	@Size(min=4, max=255)
	private String username;
	
	@NotNull(message = "{glancemes.constraints.displayname.notnull.message}")
	@Size(min=4, max=255)
	private String displayName;
	
	@NotNull(message ="{gajl.del}")
	@Size(min=8, max =255)
//	@Pattern(regexp = "^[A-Z][a-z]*")
	private String password;
	
	public User() {
		
	}
	
	public User(String username, String displayName, String password) {
		this.username = username;
		this.displayName = displayName;
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
}
