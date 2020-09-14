package com.musicD.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.musicD.dao.UserDao;
import com.musicD.dto.UserDto;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserDao userdao;
	
	@Override
	public List<UserDto> getAllUsers() {
		return userdao.findAll();
	}
}
