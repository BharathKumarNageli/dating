package com.jsp.dating.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.jsp.dating.entity.User;
import com.jsp.dating.util.UserGender;

public interface UserRepository extends JpaRepository<User, Integer> {

	List<User> findByGender(UserGender male);

	
	@Query("select u from User u where u.name like ?1")
	List<User> searchUserByName(String letters);

	@Query("select u from User u where u.email like ?1")
	List<User> searchUserByEmail(String letters);
}
