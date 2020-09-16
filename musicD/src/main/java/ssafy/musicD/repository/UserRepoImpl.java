package ssafy.musicD.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import ssafy.musicD.dto.User;

@Repository
public class UserRepoImpl implements UserRepo {
	@Autowired
	private MongoTemplate mongoTemplate;
	
	public User getUserInfo(String userId) {
		Criteria criteria = new Criteria("_id");
		criteria.is(userId);
		Query query = new Query(criteria);

		return mongoTemplate.findOne(query, User.class , "user");


//		User user = new User();
//		user.setName("donghwi");
//
//		mongoTemplate.insert(user, "user");
		
		/* 업데이트 관련
		Criteria criteria = new Criteria("name");
		criteria.is("whee");
		
		Query q2 = new Query(criteria);
		Update update = new Update();
		update.set("email", "no");
		
		mongoTemplate.updateFirst(q2, update, "user");
		
		*/
		
	}

	public void updateUserInfo(User user) {
		Criteria criteria = new Criteria("_id");
		criteria.is(user.get_id());
		Query query = new Query(criteria);

		Update update = new Update();
		update.set("nickname", user.getNickname());
		update.set("profileURL", user.getProfileURL());
		update.set("password", user.getPassword());

		mongoTemplate.updateFirst(query, update, "user");
	}

	public void deleteUser(String userId) {
		Criteria criteria = new Criteria("_id");
		criteria.is(userId);
		Query query = new Query(criteria);

		mongoTemplate.remove(query, "user");
	}
}