package ssafy.musicD.repository;

import ssafy.musicD.dto.User;

import java.util.List;

public interface FriendRepo {

	public List<User> searchFriends(String keyword);

	public List<User> waitFriendList(String userId);

	public List<User> searchNonFriend(String keyword);

	public List<User> friendList(User user);

	public void requestFriend(User user);

	public void deleteFriend(User user);

}

