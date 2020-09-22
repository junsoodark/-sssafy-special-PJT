package ssafy.musicD.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ssafy.musicD.dto.User;
import ssafy.musicD.repository.FriendRepolmpl;

import java.util.List;

@Service
public class FriendServicelmpl implements FriendService{
    @Autowired
    private FriendRepolmpl friendRepo;

    public List<User> searchFriends(String keyword){
        return friendRepo.searchFriends(keyword);
    }

//    public List<User> waitFriendList(String userId) { return friendRepo.waitFriendList(userId); }
//
//    public List<User> searchNonFriend(String keyword) { return friendRepo.searchNonFriend(keyword);}
//
//    public List<User> friendList(User user) { return friendRepo.friendList(user);}
//
//    public void requestFriend(User user) {friendRepo.requestFriend(user);}
//
//    public void deleteFriend(User user) {friendRepo.deleteFriend(user);}

}
