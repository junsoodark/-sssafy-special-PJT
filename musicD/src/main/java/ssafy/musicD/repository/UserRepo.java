package ssafy.musicD.repository;

import java.util.List;

import ssafy.musicD.dto.User;

public interface UserRepo {
	public List<User> getUserInfo(String name);
}
