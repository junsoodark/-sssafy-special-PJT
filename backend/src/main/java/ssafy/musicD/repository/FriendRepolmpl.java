package ssafy.musicD.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import ssafy.musicD.dto.User;

import java.util.List;

@Repository
public class FriendRepolmpl {
    @Autowired
    private MongoTemplate mongoTemplate;

    public List<User> searchFriends(String keyword){
        Criteria criteria = new Criteria("_id");//criteria에 유저 아이디 넣어야함 (jwt 토큰)
//        criteria.is(userId);   <-- jwt 토큰으로 아이디 구해오기
        Query query = new Query(Criteria.where("name").regex(keyword));
        List<User> keywordFriend = mongoTemplate.find(query, User.class, "user");
        Query query2 = new Query(criteria.where("FriendList").is(keywordFriend));
        return mongoTemplate.find(query2,User.class, "user");
    }


    public List<User> waitFriendList(String userId){}



    public List<User> searchNonFriend(String keyword){}

    public List<User> friendList(User user){
        Criteria criteria = new Criteria("_id");
        criteria.is(user.get_id());
        Query query = new Query(criteria.where("FriendList").in());
        return mongoTemplate.find(query,User.class,"user");
    }


    public void requestFriend(User user){}

    public void deleteFriend(User user){
        Criteria criteria = new Criteria("_id");
        criteria.is(user.get_id());
        Query query =new Query(Criteria.where("nickname").is(criteria));



    }


}
