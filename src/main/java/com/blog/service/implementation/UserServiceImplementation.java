package com.blog.service.implementation;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.blog.dto.ResponseStructure;
import com.blog.dto.UserDto;
import com.blog.entity.User;
import com.blog.exceptions.ResourceNotFoundException;
import com.blog.repository.UserRepository;
import com.blog.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class UserServiceImplementation implements UserService{
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	@Override
	public ResponseEntity<ResponseStructure<UserDto>> createUser(UserDto userDto) {
		User user = objectMapper.convertValue(userDto, User.class);
		ResponseStructure<UserDto> rs = new ResponseStructure<>();
		rs.setData(objectMapper.convertValue(userRepository.save(user), UserDto.class));
		rs.setMessage("User created");
		rs.setStatusCode(HttpStatus.CREATED.value());
		return new ResponseEntity<ResponseStructure<UserDto>>(rs,HttpStatus.CREATED);
	}

	@Override
	public ResponseEntity<ResponseStructure<UserDto>> updateUser(UserDto userDto,int userId) {
		User user = userRepository.findById(userId).orElseThrow(()->new ResourceNotFoundException("User with id: "+userId+" not found"));
		user = objectMapper.convertValue(userDto, User.class);
		user.setId(userId);
		User updatedUser = userRepository.save(user);
		ResponseStructure<UserDto> rs = new ResponseStructure<>();
		rs.setData(objectMapper.convertValue(updatedUser, UserDto.class));
		rs.setMessage("User updated");
		rs.setStatusCode(HttpStatus.OK.value());
		return ResponseEntity.ok(rs);
	}

	@Override
	public ResponseEntity<ResponseStructure<UserDto>> getUserById(int userId) {
		ResponseStructure<UserDto> rs = new ResponseStructure<>();
		rs.setData(objectMapper.convertValue(userRepository.findById(userId).orElseThrow(()->new ResourceNotFoundException("User with id: "+userId+" not found")), UserDto.class));
		rs.setMessage("user found");
		rs.setStatusCode(HttpStatus.OK.value());
		return ResponseEntity.ok(rs);
	}

	@Override
	public ResponseEntity<ResponseStructure<List<UserDto>>> getAllUsers() {
		ResponseStructure<List<UserDto>> rs = new ResponseStructure<>();
		rs.setData(userRepository.findAll().stream().map(n -> objectMapper.convertValue(n, UserDto.class)).collect(Collectors.toList()));
		rs.setMessage("users found");
		rs.setStatusCode(HttpStatus.OK.value());
 		return ResponseEntity.ok(rs);
	}

	@Override
	public ResponseEntity<String> delete(int userId) {
		User user = userRepository.findById(userId).orElseThrow(()->new ResourceNotFoundException("User with ID: "+userId+" not found"));
		userRepository.delete(user);
		return new ResponseEntity<String>("User deleted",HttpStatus.NO_CONTENT);
	}

}
