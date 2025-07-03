package com.jsp.dating.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jsp.dating.dao.UserDao;
import com.jsp.dating.dto.MatchingUser;
import com.jsp.dating.entity.User;
import com.jsp.dating.util.UserGender;
import com.jsp.dating.util.UserSorting;


@Service
public class UserService {
	@Autowired
	UserDao userDao;

	public ResponseEntity<?> saveUser(User user) {
		User savedUser = userDao.saveUser(user);
		return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
	}

	public ResponseEntity<?> findAllMaleUsers() {
		List<User> allMaleUsers = userDao.findAllMaleUsers();
		if(allMaleUsers.isEmpty())
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No male users found");
		return ResponseEntity.ok(allMaleUsers);
	}
	
	public ResponseEntity<?> findAllFemaleUsers() {
		List<User> allMaleUsers = userDao.findAllFemaleUsers();
		if(allMaleUsers.isEmpty())
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No male users found");
		return ResponseEntity.ok(allMaleUsers);
	}

	
	
	public ResponseEntity<?> findBestMatches(int id, int matches) {
		Optional<User> optionalUser= userDao.findUserById(id);
		
		if(optionalUser.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Invalid id");
		}
		
		User user = optionalUser.get();
		List<User> users = null ;
		
		if(user.getGender().equals(UserGender.MALE))
			users = userDao.findAllFemaleUsers();
		else
			users = userDao.findAllMaleUsers();
		
		
		List<MatchingUser> matchingUsers = new ArrayList<MatchingUser>();
		
		for(User u : users) {
			MatchingUser mu = new MatchingUser();
			mu.setId(u.getId());
			mu.setName(u.getName());
			mu.setEmail(u.getEmail());
			mu.setPhone(u.getPhone());
			mu.setAge(u.getAge());
			mu.setInterests(u.getInterests());
			mu.setGender(u.getGender());
			
			mu.setAgeDiff(Math.abs(user.getAge()-u.getAge()));
			
			
			List<String> interests1 = user.getInterests();
			List<String> interests2 = u.getInterests();
			interests1.retainAll(interests2);
			int count = interests1.size();
			mu.setMatchInterestCount(count);
			matchingUsers.add(mu);
		}
		
		Collections.sort(matchingUsers, new UserSorting());
		
		List<MatchingUser> requiredmatchingUsers = new ArrayList<MatchingUser>();;
		for(MatchingUser u : matchingUsers)
			if(matches-->0)
				requiredmatchingUsers.add(u);
			else
				break;
		
		return ResponseEntity.ok(requiredmatchingUsers);
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public ResponseEntity<?> findUsers(UserGender gender){
		List<User> users = userDao.findUsers(gender);
		if(users.isEmpty())
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No users found");
		return ResponseEntity.ok(users);
	}

	
}
