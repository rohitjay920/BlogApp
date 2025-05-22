package com.blog.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.blog.dto.ResponseStructure;
import com.blog.dto.UserDto;

public interface UserService {
	
	ResponseEntity<ResponseStructure<UserDto>> createUser(UserDto userDto);
	ResponseEntity<ResponseStructure<UserDto>> updateUser(UserDto userDto,int userId);
	ResponseEntity<ResponseStructure<UserDto>> getUserById(int userId);
	ResponseEntity<ResponseStructure<List<UserDto>>> getAllUsers();
	ResponseEntity<String> delete(int userId);
}