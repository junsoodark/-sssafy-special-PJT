package ssafy.musicD.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;
import ssafy.musicD.dto.User;

import java.util.ArrayList;
import java.util.List;

@Repository
public class FriendRepolmpl {
    @Autowired
    private MongoTemplate mongoTemplate;
    private boolean False;

    public List<User> searchFriends(String keyword, String userId) {
        Query query1 = new Query(Criteria.where("_id").is(userId));
        User userSelf = mongoTemplate.findOne(query1, User.class, "user");
        List<String> userSelfFriend = userSelf.getFriends();                                  //1 요청한사람의 친구 리스트
        List<User> searchFriendOfKeyword = new ArrayList<>();
        for (int i = 0; i < userSelfFriend.size(); i++) {
            System.out.println(userSelfFriend.get(i));
            Query query2 = new Query(Criteria.where("_id").is(userSelfFriend.get(i)));
            User temp = mongoTemplate.findOne(query2, User.class, "user");
            System.out.println(temp);
            System.out.println(keyword);
            if (temp.getNickname().contains(keyword)) {
                searchFriendOfKeyword.add(temp);
            }

        }
        return searchFriendOfKeyword;
    }


    public List<User> waitFriendList(String userId){
        Query query = new Query(Criteria.where("_id").is(userId));
        User temp = mongoTemplate.findOne(query, User.class,"user");
        List<String> waitUserFriendList = temp.getWaitFriends();
        List<User> waitFriendList = new ArrayList<>();
        for (int i = 0; i<waitUserFriendList.size(); i++){
            String friendId = waitUserFriendList.get(i);
            Query query1 = new Query(Criteria.where("_id").is(friendId));
            User friend = mongoTemplate.findOne(query1, User.class,"user");
            waitFriendList.add(friend);
        }
        return waitFriendList;
    }



    public List<User> searchNonFriend(String keyword, String userId){
        Query query = new Query(Criteria.where("Nickname").regex(keyword));
        List<User> keywordFriend = mongoTemplate.find(query, User.class, "user");
        System.out.println(keywordFriend);
        Query query2 = new Query(Criteria.where("_id").is(userId));
        User userInfo = mongoTemplate.findOne(query2, User.class,"user");
        List<String> allUserOfKeyword = new ArrayList<>();
        List<User> nonFriendList = new ArrayList<>();
        for (User user : keywordFriend) {
            allUserOfKeyword.add(user.get_id());
        }
        System.out.println(allUserOfKeyword);
        List<String> userInfoFriend = userInfo.getFriends();
            for (int i = 0; i < allUserOfKeyword.size(); i++) {
                String checkUserId = allUserOfKeyword.get(i);
                if (userInfoFriend.contains(checkUserId)) {
                    continue;
                }else{
                    Query query3 = new Query(Criteria.where("_id").is(checkUserId));
                    User temp = mongoTemplate.findOne(query3, User.class,"user");
                    nonFriendList.add(temp);
                }
                }
        return nonFriendList;
    }

    public List<User> friendList(String userId){
        Query query = new Query(Criteria.where("_id").is(userId));
        User temp = mongoTemplate.findOne(query, User.class,"user");
        List<String> userFriendList = temp.getFriends();
        List<User> friendList = new ArrayList<>();
        for (int i = 0; i<userFriendList.size(); i++){
            String friendId = userFriendList.get(i);
            Query query1 = new Query(Criteria.where("_id").is(friendId));
            User friend = mongoTemplate.findOne(query1, User.class,"user");
            friendList.add(friend);
        }
        return friendList;
    }

    public void requestFriend(String userId,String friendId){
        Query query = new Query(Criteria.where("_id").is(friendId));
        User receiveUser = mongoTemplate.findOne(query, User.class,"user");
        List<String> waitFriends = receiveUser.getWaitFriends();
        if (waitFriends.contains(userId)==False){
            waitFriends.add(userId);
            Update update = new Update();
            update.set("waitFriends", waitFriends);
            mongoTemplate.updateFirst(query, update, "user");
        }
    }

    public void acceptFriend(String userId,String friendId){
        Query query = new Query(Criteria.where("_id").is(userId));
        Query query1 = new Query(Criteria.where("_id").is(friendId));
        User sendUser = mongoTemplate.findOne(query1, User.class,"user");
        User receiveUser = mongoTemplate.findOne(query, User.class, "user");
        List<String> receiveListed = receiveUser.getFriends();
        List<String> sendListed = sendUser.getFriends();
        receiveListed.add(friendId);
        sendListed.add(userId);
        Update update = new Update();
        update.set("friends",receiveListed);
        mongoTemplate.updateFirst(query,update,"user");
        Update update1 = new Update();
        update1.set("friends",sendListed);
        mongoTemplate.updateFirst(query1,update1,"user");

        List<String> waitSendListed = sendUser.getWaitFriends();
        List<String> waitReceiveListed = receiveUser.getWaitFriends();
        waitSendListed.remove(userId);
        waitReceiveListed.remove(friendId);

        Update update2 = new Update();
        update2.set("waitFriends",waitSendListed);
        mongoTemplate.updateFirst(query1,update2,"user");
        Update update3 = new Update();
        update3.set("waitFriends",waitReceiveListed);
        mongoTemplate.updateFirst(query,update3,"user");
    }

    public void deleteFriend(String userId,String friendId){
        Query query = new Query(Criteria.where("_id").is(userId));
        Query query1 = new Query(Criteria.where("_id").is(friendId));
        User receiveUser = mongoTemplate.findOne(query, User.class, "user");
        User sendUser = mongoTemplate.findOne(query1, User.class,"user");
        List<String> receivelisted = receiveUser.getFriends();
        List<String> sendlisted = sendUser.getFriends();
        receivelisted.remove(friendId);
        sendlisted.remove(userId);
        Update update = new Update();
        update.set("friends",receivelisted);
        mongoTemplate.updateFirst(query,update,"user");
        Update update1 = new Update();
        update.set("friends",sendlisted);
        mongoTemplate.updateFirst(query1,update,"user");

    }


}
