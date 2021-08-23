package com.cfmaddressbook.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cfmaddressbook.demo.entities.User;
import com.cfmaddressbook.demo.repositories.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepos; 
	
	public void saveUser(User newUser){
		userRepos.save(newUser);
	}
	
	public List<User> findAllUser(){
		
		return userRepos.findAll();
	}
	
	public User findByEmail(String email) {
		
		if(email == null) {
			return userRepos.findByEmail(email);
		}
		
		return userRepos.findByEmail(email); 
	}
}
