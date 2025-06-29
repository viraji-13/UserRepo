 package com.deploy.user.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.deploy.user.dto.UserDto;
import com.deploy.user.entity.AppUser;
import com.deploy.user.exception.InvalidUserException;
import com.deploy.user.service.ServiceImpl;

import jakarta.validation.Valid;

@RestController
@CrossOrigin("*")
public class UserController {

@Autowired
ServiceImpl s;
	
	@GetMapping("/")
	public ResponseEntity<List<AppUser>> getAllUser(){
		return new ResponseEntity<List<AppUser>>(s.getAllUser(), HttpStatus.OK);
	}
	
	
	
	@GetMapping("/welcome")
	public String welcome(){
		return "welcome to user service";
	}

	@GetMapping("/{id}")
	@Cacheable(value = "Users", key = "#id")
	public ResponseEntity<AppUser> getUserById(@PathVariable int id) throws InvalidUserException{
		System.out.println("get method called");
		return new ResponseEntity<AppUser>(s.getUserById(id), HttpStatus.OK);
	}
	
	@GetMapping("/auth/{username}/{password}")
	public ResponseEntity<AppUser> authuser(@PathVariable String username ,@PathVariable String password){
		return new ResponseEntity<AppUser>(s.authUser(username,password), HttpStatus.OK);
	}
	
	@PostMapping("/")
	public ResponseEntity<AppUser> addUser(@Valid @RequestBody UserDto udto){
		return new ResponseEntity<AppUser>(s.addUser(udto), HttpStatus.OK);
	}
	
	@PutMapping("/update/{id}")
	@CachePut(value = "Users", key="#id")
	public ResponseEntity<AppUser> updateUser(@PathVariable int id, @RequestBody UserDto udto) throws InvalidUserException{
	System.out.println("update called");
		return new ResponseEntity<AppUser>(s.updateUser(udto, id), HttpStatus.OK);	
	}
	
	@DeleteMapping("/delete/{id}")
	@CacheEvict(value = "Users", key="#id")
	public ResponseEntity<AppUser> deleteUser(@PathVariable int id) throws InvalidUserException{
		System.out.println("delete called");
		return new ResponseEntity<AppUser>(s.deleteUserById(id), HttpStatus.OK);
	}
	
}
