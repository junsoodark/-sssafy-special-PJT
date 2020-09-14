package com.musicD.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.musicD.dto.UserDto;
import com.musicD.service.UserServiceImpl;

@RestController
@RequestMapping("/api")
public class UserController {

	@Autowired
	private UserServiceImpl userService;
	
	@GetMapping("/user")
	public void getAllUser(){
		System.out.println("wow");
		List<UserDto> users = userService.getAllUsers();
		System.out.println(users.size());
	}

}
