package com.example.demo.service;

import java.util.List;

import com.example.demo.entities.User;
import com.example.demo.payloads.UserDto;

public interface UserService {
	UserDto createUser(UserDto userDto);

	UserDto UpdateUser(UserDto user, Integer userId);

	UserDto getUserById(Integer userId);

	List<UserDto> getAllUsers();

	void deleteUser(Integer userId);
}
