package ssafy.musicD.service;

import java.util.List;

import ssafy.musicD.dto.User;

public interface UserService {
	public User getUserInfo(String userId);
	public void deleteUser(String userId);
}
