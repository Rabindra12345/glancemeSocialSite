package com.glanceme.glanceme;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
//import org.apache.catalina.User;
//import org.junit.Test;
//import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.glanceme.glanceme.repo.UserRepository;
import com.glanceme.glanceme.shared.GenericResponse;
import com.glanceme.glanceme.user.User;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class UserControllerTest {

	private static final String API_1_0_USERS = "/api/1.0/users";
	@Autowired
	TestRestTemplate testRestTemplate;
	
	@Autowired
	UserRepository userRepository;
	
	@Before
	public void cleanup() {
		userRepository.deleteAll();
	}
	
	@Test
	public void postUser_whenUserIsValid_receiveOk() {
		
//		User user = new User();
		User user = createValidUser();
		
//		ResponseEntity<Object> response = testRestTemplate.getForEntity(API_1_0_USERS, Object.class);
		
//		ResponseEntity<Object> response = testRestTemplate.postForEntity(API_1_0_USERS,user, Object.class);
		
		ResponseEntity<Object> response = postSignup(user,Object.class);;


		
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
	}

	
	@Test
	public void postUser_whenUserIsValid_saveToDatabase() {
		
		User user = createValidUser();
		postSignup(user,Object.class);;
//		assertThat(userRepository.count()).isEqualTo(1);
		assertThat(userRepository.count()).isEqualTo(1);
		
	}
	
	@Test
	public void postUser_whenUserIsValid_passwordIsHashedInDatabase() {
		
		User user = createValidUser();
//		testRestTemplate.postForEntity(API_1_0_USERS,user, Object.class);
		postSignup(user,Object.class);


		List<User> users = userRepository.findAll();
		
		User userDb = users.get(0);
		
		assertThat(userDb.getPassword()).isNotEqualTo(user.getPassword());
	}
	
	
	@Test
	public void postUser_whenUserIsValid_receiveSuccessMessage() {
		
//		User user = new User();
		User user = createValidUser();
		
//		ResponseEntity<Object> response = testRestTemplate.getForEntity(API_1_0_USERS, Object.class);
		
		ResponseEntity<GenericResponse> response = postSignup(user, GenericResponse.class);

		
		assertThat(response.getBody().getMessage()).isNotNull();
	}
	
	@Test
	public void postUser_whenUserHasNullUsername_receiveBadRequest() {
		User user = createValidUser();
		user.setUsername(null);
		ResponseEntity<Object> response = postSignup(user,Object.class);
//		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
	}
	
	@Test
	public void postUser_whenUserHasNullDisplayName_receiveBadRequest() {
		User user = createValidUser();
		user.setDisplayName(null);	
		ResponseEntity<Object> response = postSignup(user,Object.class);
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
	}
	
	@Test
	public void postUser_whenUserHasNullPassword_receiveBadRequest() {
		User user = createValidUser();
		user.setPassword(null);
		ResponseEntity<Object> response = postSignup(user,Object.class);
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
	}
	
//	@Test
//	public void postUser_whenUserHasUsernameWithLessThanRequired_receiveBadRequest() {
//		User user = createValidUser();
//		user.setUsername("ra");
//		ResponseEntity<Object> response = postSignup(user,Object.class);
////		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
//		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
//	}
//	
//	@Test
//	public void postUser_whenUserHasDisplayNameLessThanRequired_receiveBadRequest() {
//		User user = createValidUser();
//		user.setDisplayName("ram");
//		ResponseEntity<Object> response = postSignup(user,Object.class);
////		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
//		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
//	}
//	
//	@Test
//	public void postUser_whenUserHasPasswordWithLessThanRequired_receiveBadRequest() {
//		User user = createValidUser();
//		user.setPassword("ra2Sa");
//		ResponseEntity<Object> response = postSignup(user,Object.class);
////		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
//		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
//	}
//	
//	@Test
//	public void postUser_whenUserNameExceedsTheLengthLimits_receiveBadRequest() {
//		User user = createValidUser();
//		
//		String stringOf256Char = IntStream.rangeClosed(1, 256).mapToObj(x -> "a").collect(Collectors.joining());
//		user.setUsername(stringOf256Char);
//		ResponseEntity<Object> response = postSignup(user,Object.class);
////		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
//		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
//	}
//	
//	@Test
//	public void postUser_whenUserHasDisplayNameThatExceedsTheLength_receiveBadRequest() {
//		User user = createValidUser();
//		
//		String stringOf256Char = IntStream.rangeClosed(1, 256).mapToObj(x -> "a").collect(Collectors.joining());
//		user.setDisplayName(stringOf256Char);
//		ResponseEntity<Object> response = postSignup(user,Object.class);
////		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
//		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
//	}
//	
//	@Test
//	public void postUser_whenUserHasPasswordThatExceedsTheLength_receiveBadRequest() {
//		User user = createValidUser();
//		
//		String stringOf256Char = IntStream.rangeClosed(1, 256).mapToObj(x -> "a").collect(Collectors.joining());
//		user.setPassword(stringOf256Char +"R1");
//		ResponseEntity<Object> response = postSignup(user,Object.class);
////		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
//		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
//	}
//	
//	@Test
//	public void postUser_whenUserHasPasswordWithAllLowerCase_receiveBadRequest() {
//		User user = createValidUser();
//		user.setPassword("alllowercasepassword");
//		ResponseEntity<Object> response = postSignup(user,Object.class);
////		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
//		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
//	}
//	
	public <T> ResponseEntity<T> postSignup(Object request, Class<T> response){
		return testRestTemplate.postForEntity(API_1_0_USERS, request, response);
	}
	
	private User createValidUser() {
		User user = new User();
		user.setUsername("test-user");
		user.setDisplayName("test-display");
		user.setPassword("P4ssword");
		return user;
	}
	

}
