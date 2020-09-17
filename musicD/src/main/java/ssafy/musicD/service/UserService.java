package ssafy.musicD.service;

import java.util.List;
import java.util.Map;

import ssafy.musicD.dto.User;

public interface UserService {
	public List<User> getUserInfo(String name);
}
