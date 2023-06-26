package com.codewithyad.blog.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.codewithyad.blog.entities.User;
import com.codewithyad.blog.execeptions.ResourceNotFoundExeception;
import com.codewithyad.blog.repositores.UserRepositories;
@Service
public class CustomUserDetailService implements UserDetailsService{
	@Autowired
	private UserRepositories userRepo;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user=this.userRepo.findByEmail(username).orElseThrow(()->new 
				ResourceNotFoundExeception("user","email : "+username,0));
		return user;
	}

}
