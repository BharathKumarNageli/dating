package com.jsp.dating.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.jsp.dating.entity.User;
import com.jsp.dating.util.UserGender;

public interface UserRepository extends JpaRepository<User, Integer> {

	List<User> findByGender(UserGender male);
}
