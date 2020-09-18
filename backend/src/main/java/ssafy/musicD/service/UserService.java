package ssafy.musicD.service;

import java.util.List;

import ssafy.musicD.dto.User;

public interface UserService {
	public void signUp(User user);
	public User getUserInfo(String userId);
	public void updateUserInfo(User user);
	public void deleteUser(String userId);
	public String checkEmail(String email);
	public String checkNickname(String nickname);
}
