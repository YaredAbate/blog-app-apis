package com.codewithyad.blog.services;

import java.util.List;

import com.codewithyad.blog.payloads.UserDto;

public interface UserServices {
	UserDto createUser(UserDto user);
	UserDto updateUser(UserDto user,Integer userId);
	UserDto getUserById(Integer userId);
	List<UserDto> getAllUsers();
	void deleteUser(Integer userId);
}
