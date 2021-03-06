package com.glanceme.glanceme.user;

import java.beans.Transient;
import java.util.Collection;
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

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonView;

//@Data
@Entity
public class User implements UserDetails{
	

	@Id
	@GeneratedValue
	@JsonView(Views.Base.class)
	private long id ;
//	@NotNull
	
	//NOTE: Here if we want to translate this english response message to another language
	//its always a good practice to extract the static field out of our code and 
//	@NotEmpty
//	(message="Username should not be empty")
//	@NotEmpty
	@UniqueUsername
	@Size(min=4, max=255)
	@JsonView(Views.Base.class)
	private String username;
	
	@NotNull(message = "{glancemes.constraints.displayname.notnull.message}")
	@Size(min=4, max=255)
	@JsonView(Views.Base.class)
	private String displayName;
	
	@NotNull(message ="{gajl.del}")
	@Size(min=8, max =255)
//	@Pattern(regexp = "^[A-Z][a-z]*")
	@JsonView(Views.Base.class)
	private String password;
	
	@JsonView(Views.Base.class)
	private String image;
	
	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public User() {
		
	}
	
	public User(String username, String displayName, String password) {
		this.username = username;
		this.displayName = displayName;
		this.password = password;
	}

	
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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

	@Override
	@Transient
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return AuthorityUtils.createAuthorityList("Role_USER");
	}

	@Override
	@Transient
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	@Transient
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	@Transient
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	@Transient
	public boolean isEnabled() {
		return true;
	}
	
}
