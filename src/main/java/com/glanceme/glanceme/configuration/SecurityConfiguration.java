package com.glanceme.glanceme.configuration;

import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;

@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter{

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http.csrf().disable();
//			http.httpBasic().authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED));
		//instead of above HttpStatusEntryPoint we could use our custom authentication entry point ..as below
			
		http.httpBasic().authenticationEntryPoint(new BasicAuthenticationEntryPoint());
		http
			.authorizeHttpRequests().antMatchers(HttpMethod.POST, "/api/1.0/login").authenticated()
			.and()
			//and above code is to apply no authentication to any other endpoints for instance
			// for 'api/1.0/users'
			.authorizeHttpRequests().anyRequest().permitAll();
		
//		http.authorizeRequests().antMatchers(HttpMethod.POST,"api/1.0/users").permitAll();
		
	}
}
