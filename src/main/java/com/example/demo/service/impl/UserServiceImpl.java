package com.example.demo.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entities.User;
import com.example.demo.exceptions.ResourseNotFoundException;
import com.example.demo.payloads.UserDto;
import com.example.demo.repository.UserRepo;
import com.example.demo.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepo userRepo;

	@Override
	public UserDto createUser(UserDto userDto) {
		User user = this.DtoToEntity(userDto);
		User saveduser = this.userRepo.save(user);
		return this.entityToDto(saveduser);
	}

	@Override
	public UserDto UpdateUser(UserDto userDto, Integer userId) {
		User user = this.userRepo.findById(userId)
				.orElseThrow(() -> new ResourseNotFoundException("User", "id", userId));
		user.setName(userDto.getName());
		user.setEmail(userDto.getEmail());
		user.setPassword(userDto.getPassword());
		user.setAbout(userDto.getAbout());

		User savedUser = this.userRepo.save(user);
		UserDto userDto1 = this.entityToDto(savedUser);

		return userDto1;
	}

	@Override
	public UserDto getUserById(Integer userId) {
		User user = this.userRepo.findById(userId)
				.orElseThrow(() -> new ResourseNotFoundException("User", "id", userId));

		UserDto userDto = entityToDto(user);
		return userDto;
	}

	@Override
	public List<UserDto> getAllUsers() {
		// TODO Auto-generated method stub
		return this.userRepo.findAll().stream().map(this::entityToDto).collect(Collectors.toList());
	}

	@Override
	public void deleteUser(Integer userId) {
		User user = this.userRepo.findById(userId)
				.orElseThrow(() -> new ResourseNotFoundException("User", "id", userId));
		this.userRepo.delete(user);

	}

	public User DtoToEntity(UserDto userDto) {
		User user = new User();
		user.setId(userDto.getId());
		user.setName(userDto.getName());
		user.setEmail(userDto.getEmail());
		user.setPassword(userDto.getPassword());
		user.setAbout(userDto.getAbout());
		return user;
	}

	public UserDto entityToDto(User user) {
		UserDto userDto = new UserDto();
		userDto.setId(user.getId());
		userDto.setName(user.getName());
		userDto.setEmail(user.getEmail());
		userDto.setPassword(user.getPassword());
		userDto.setAbout(user.getAbout());

		return userDto;
	}

}
