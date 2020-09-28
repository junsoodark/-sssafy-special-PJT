package ssafy.musicD.repository;

import ssafy.musicD.dto.User;

import java.util.List;

public interface FriendRepo {

	public List<User> searchFriends(String keyword,String userId);

	public List<User> waitFriendList(User user);

	public List<User> searchNonFriend(String keyword,String userId);

	public List<User> friendList(String userId);

	public void requestFriend(String userId,String friendId);

	public void acceptFriend(String userId,String friendId);

	public void deleteFriend(String userId,String friendId);

}

