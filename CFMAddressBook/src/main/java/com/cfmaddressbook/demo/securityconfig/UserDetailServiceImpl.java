package com.cfmaddressbook.demo.securityconfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.cfmaddressbook.demo.entities.User;
import com.cfmaddressbook.demo.services.UserService;

public class UserDetailServiceImpl implements UserDetailsService {

	@Autowired
	UserService userService;
	
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userService.findByEmail(username);
        
        if (user == null) {
            throw new UsernameNotFoundException("Could not find user");
        }
         
        return new MyUserDetails(user);
	}

}
