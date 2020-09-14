package ssafy.musicD.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ssafy.musicD.dto.User;
import ssafy.musicD.repository.UserRepoImpl;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	UserRepoImpl userRepo;
	
	public List<User> getUserInfo(String name) {
		return userRepo.getUserInfo(name);
	}
}
