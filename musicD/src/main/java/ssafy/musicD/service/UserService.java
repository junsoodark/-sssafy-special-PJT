package ssafy.musicD.service;

import java.util.List;

import ssafy.musicD.dto.User;

public interface UserService {
	public List<User> getUserInfo(String name);
	public void deleteUser(String userId);
}
