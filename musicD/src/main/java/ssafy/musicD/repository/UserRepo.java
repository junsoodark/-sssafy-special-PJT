package ssafy.musicD.repository;

import java.util.List;

import ssafy.musicD.dto.User;

public interface UserRepo {
	public User getUserInfo(String userId);
	public void updateUserInfo(User user);
	public void deleteUser(String userId);
	public String checkEmail(String email);
	public String checkNickname(String nickname);
}
