package ssafy.musicD.service;

import ssafy.musicD.dto.MemberDto;

import java.util.List;

public interface FriendService {

    public List<MemberDto> searchFriends(String keyword,String userId);

    public List<MemberDto> waitFriendList(String userId);

    public List<MemberDto> searchNonFriend(String keyword,String userId);

    public List<MemberDto> friendList(String userId);

    public void requestFriend(String userId,String friendId);

    public void acceptFriend(String userId,String friendId);

    public void deleteFriend(String userId,String friendId);

    public void refuseFriend(String userId,String friendId);
}

