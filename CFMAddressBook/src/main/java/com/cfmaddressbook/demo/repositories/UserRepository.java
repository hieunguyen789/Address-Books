package com.cfmaddressbook.demo.repositories;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;


import com.cfmaddressbook.demo.entities.User;

public interface UserRepository extends JpaRepository<User, Integer>{
	
	public List<User> findAll();
	
	public User findByEmail(String email);


}
