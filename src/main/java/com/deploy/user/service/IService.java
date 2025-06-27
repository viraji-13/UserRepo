package com.deploy.user.service;

import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.deploy.user.dto.UserDto;
import com.deploy.user.entity.AppUser;
import com.deploy.user.exception.InvalidUserException;

@Component
public interface IService {

	public AppUser addUser(UserDto u);
	public AppUser getUserById(int id) throws InvalidUserException;
	public List<AppUser> getAllUser();
	public AppUser updateUser(UserDto u , int id) throws InvalidUserException;
	public AppUser deleteUserById(int Id) throws InvalidUserException;
	
}
