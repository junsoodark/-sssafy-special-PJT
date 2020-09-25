package ssafy.musicD.repository;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import com.mongodb.BasicDBObject;

import ssafy.musicD.Domain.Member;

@Repository
public class UserRepoImpl implements UserRepo2 {
	@Autowired
	private MongoTemplate mongoTemplate;

	@Override
	public void updateUserInfo(Member user) {
		Criteria criteria = new Criteria("_id");
		criteria.is(user.getId());
		Query query = new Query(criteria);

		Update update = new Update();
		update.set("nickname", user.getNickname());
		update.set("profileURL", user.getProfileURL());

		mongoTemplate.updateFirst(query, update, "user");
	}

//	public void deleteUser(String userId) {
//		Criteria criteria = new Criteria("_id");
//		criteria.is(userId);
//		Query query = new Query(criteria);
//
//		mongoTemplate.remove(query, "user");
//	}

}