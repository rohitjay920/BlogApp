package com.blog.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blog.dto.ResponseStructure;
import com.blog.dto.UserDto;
import com.blog.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@PostMapping("/save")
	public ResponseEntity<ResponseStructure<UserDto>> createUser(@RequestBody UserDto userDto){
		return userService.createUser(userDto);
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<ResponseStructure<UserDto>> updateUser(@RequestBody UserDto userDto, @PathVariable(name="id") int userId){
		return userService.updateUser(userDto, userId);
	}
	
	@GetMapping("/get/{id}")
	public ResponseEntity<ResponseStructure<UserDto>> getUserById(@PathVariable(name="id") int userId){
		return userService.getUserById(userId);
	}
	
	@GetMapping("/getAll")
	public ResponseEntity<ResponseStructure<List<UserDto>>> getAllUsers(){
		return userService.getAllUsers();
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deleteUser(@PathVariable(name="id") int userId){
		return userService.delete(userId);
	}
	
}
