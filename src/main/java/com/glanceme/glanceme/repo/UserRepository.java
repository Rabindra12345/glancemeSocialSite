package com.glanceme.glanceme.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.glanceme.glanceme.user.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	
//	List<User> findByUsername(String username);
	
	User findByUsername(String username);
	
}
