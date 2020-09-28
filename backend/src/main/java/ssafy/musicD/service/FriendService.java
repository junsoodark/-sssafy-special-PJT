package ssafy.musicD.service;

import ssafy.musicD.dto.User;

import java.util.List;

public interface FriendService {

    public List<User> searchFriends(String keyword,String userId);

    public List<User> waitFriendList(String userId);

    public List<User> searchNonFriend(String keyword,String userId);

    public List<User> friendList(String userId);

    public void requestFriend(String userId,String friendId);

    public void acceptFriend(String userId,String friendId);

    public void deleteFriend(String userId,String friendId);

}

