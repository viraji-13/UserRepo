package com.deploy.user.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.deploy.user.dto.UserDto;
import com.deploy.user.entity.AppUser;
import com.deploy.user.exception.InvalidUserException;
import com.deploy.user.repo.UserRepo;

@Service
public class ServiceImpl implements IService {
	
@Autowired
UserRepo ur;
	
	@Override
	public AppUser addUser(UserDto udto) {
		AppUser u = new AppUser();
		u.setName(udto.getName());
		u.setUserName(udto.getUserName());
		u.setPassword(udto.getPassword());
		u.setMail(udto.getMail());
		ur.save(u);
		return u;
	}

	@Override
	public AppUser getUserById(int id) throws InvalidUserException {
	Optional<AppUser> o = ur.findById(id);
	if(o.isPresent()) {
		return o.get();
	}
	else throw new InvalidUserException("No user found");
	}

	@Override
	public List<AppUser> getAllUser() {
	
		return ur.findAll();
	}

	@Override
	public AppUser updateUser(UserDto udto , int id) throws InvalidUserException {
		Optional<AppUser> o = ur.findById(id);
		System.out.println(udto.toString());
		if(o.isPresent()) {
			AppUser u = o.get();
			u.setName(udto.getName());
			u.setUserName(udto.getUserName());
			u.setPassword(udto.getPassword());
			u.setMail(udto.getMail());
			ur.save(u);
			return u;
		}
		else throw new InvalidUserException("No user found");
	}

	@Override
	public AppUser deleteUserById(int id) throws InvalidUserException {
		Optional<AppUser> o = ur.findById(id);
		if(o.isPresent()) {
			AppUser u = o.get();
			ur.deleteById(id);
			return u;
		}
		else throw new InvalidUserException("No user found");
	}

	public AppUser authUser(String username, String password) throws InvalidUserException {
		Optional<AppUser> o = ur.findByUserName(username);
		if(o.isPresent()) {
			AppUser au = o.get();
			if(au.getPassword().equals(password)) {
				return au;
			}
			else throw new InvalidUserException("no user found");
		}
		
		else throw new InvalidUserException("no user found");
	}

	

}
