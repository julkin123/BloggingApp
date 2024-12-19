package com.example.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.payloads.ApiResponse;
import com.example.demo.payloads.UserDto;
import com.example.demo.service.UserService;

import jakarta.validation.Valid;

@RestController
@CrossOrigin
@RequestMapping("/api/user")

public class UserController {
	@Autowired
	private UserService userService;

	@PostMapping("/")
	public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto) {
		UserDto userDto1 = this.userService.createUser(userDto);
		return new ResponseEntity<>(userDto1, HttpStatus.CREATED);
	}

	@GetMapping("/")
	public ResponseEntity<List<UserDto>> getAllUsers() {

		return new ResponseEntity<List<UserDto>>(this.userService.getAllUsers(), HttpStatus.OK);
	}

	@PutMapping("/{id}")
	public ResponseEntity<UserDto> UpdateUser(@RequestBody UserDto userDto, @PathVariable Integer id) {
		return new ResponseEntity<UserDto>(this.userService.UpdateUser(userDto, id), HttpStatus.OK);

	}

	@GetMapping("/{id}")
	public ResponseEntity<UserDto> getUserById(@PathVariable Integer id) {

		return new ResponseEntity<UserDto>(this.userService.getUserById(id), HttpStatus.OK);
	}

	@DeleteMapping("/{userId}")
	public ResponseEntity<ApiResponse> deleteUser(@PathVariable Integer userId) {
		this.userService.deleteUser(userId);
		return new ResponseEntity<ApiResponse>(new ApiResponse("deleted", true), HttpStatus.OK);

	}

}
