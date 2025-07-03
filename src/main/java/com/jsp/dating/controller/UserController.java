package com.jsp.dating.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.jsp.dating.entity.User;
import com.jsp.dating.service.UserService;
import com.jsp.dating.util.UserGender;

@RestController
public class UserController {
	@Autowired
	UserService userService;
	
	
	
	@PostMapping("/users")
	public ResponseEntity<?> saveUser(@RequestBody User user){
		return userService.saveUser(user);
	}
	
	
	
	@GetMapping("/users/gender/male")
	public ResponseEntity<?> findAllMaleUsers(){
		return userService.findAllMaleUsers();
	}
	
	@GetMapping("/users/gender/female")
	public ResponseEntity<?> findAllFemaleUsers(){
		return userService.findAllFemaleUsers();
	}
	
	
	
	@GetMapping("/users/best-match/{id}/{matches}")
	public ResponseEntity<?> findBestMatches(@PathVariable int id, @PathVariable int matches){
		return userService.findBestMatches(id, matches);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	@GetMapping("/users/genderfind/{gender}")
	public ResponseEntity<?> findUsers(@PathVariable UserGender gender){
		return userService.findUsers(gender);
	}
	
	
	
}
