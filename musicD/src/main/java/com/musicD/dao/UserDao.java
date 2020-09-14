package com.musicD.dao;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.musicD.dto.UserDto;

public interface UserDao extends MongoRepository<UserDto, Long> {
	public List<UserDto> findAll();
}
