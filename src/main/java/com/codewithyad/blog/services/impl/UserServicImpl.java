package com.codewithyad.blog.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codewithyad.blog.entities.User;
import com.codewithyad.blog.execeptions.ResourceNotFoundExeception;
import com.codewithyad.blog.payloads.UserDto;
import com.codewithyad.blog.repositores.*;
import com.codewithyad.blog.services.UserServices;
@Service
public class UserServicImpl implements UserServices {
	@Autowired
	private UserRepositories userRepo;
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public UserDto createUser(UserDto userDto) {
		User user=this.dtoToUser(userDto);
		User savedUser=this.userRepo.save(user);
		return this.userToDto(savedUser);
	}

	@Override
	public UserDto updateUser(UserDto userDto, Integer userId) {
		User user=this.userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundExeception("user","Id",userId));
		user.setName(userDto.getName());
		user.setEmail(userDto.getEmail());
		user.setPassword(userDto.getPassword());
		user.setAbout(userDto.getAbout());
		User savedUser=this.userRepo.save(user);
		UserDto userDto1=this.userToDto(savedUser);
		return userDto1;
	}

	@Override
	public UserDto getUserById(Integer userId) {
		User user=this.userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundExeception("user","Id",userId));
		return this.userToDto(user);
	}

	@Override
	public List<UserDto> getAllUsers() {
		List<User> users=this.userRepo.findAll();
		List<UserDto> userDtos=users.stream().map(user->this.userToDto(user)).collect(Collectors.toList());
		return userDtos;
	}

	@Override
	public void deleteUser(Integer userId) {
		User user=this.userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundExeception("user","Id",userId));
		this.userRepo.delete(user);

	}
	private User dtoToUser(UserDto userDto) {
		User user=this.modelMapper.map(userDto,User.class);
		/*user.setId(userDto.getId());
		user.setName(userDto.getName());
		user.setEmail(userDto.getEmail());
		user.setPassword(userDto.getPassword());
		user.setAbout(userDto.getAbout());*/
		return user;
	}
	private UserDto userToDto(User user) {
		UserDto userDto=this.modelMapper.map(user,UserDto.class);
		/*userDto.setId(user.getId());
		userDto.setName(user.getName());
		userDto.setEmail(user.getEmail());
		userDto.setPassword(user.getPassword());
		userDto.setAbout(user.getAbout());*/
		return userDto;
	}

}
