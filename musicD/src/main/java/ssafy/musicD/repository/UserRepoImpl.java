package ssafy.musicD.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import ssafy.musicD.dto.User;

@Repository
public class UserRepoImpl implements UserRepo {
	@Autowired
	private MongoTemplate mongoTemplate;
	
	public List<User> getUserInfo(String name) {
		Query query = new Query(new Criteria().andOperator(Criteria.where("name").is(name)));

		User user = new User();
		user.setName("donghwi");

		mongoTemplate.insert(user, "user");
		
		/* 업데이트 관련
		Criteria criteria = new Criteria("name");
		criteria.is("whee");
		
		Query q2 = new Query(criteria);
		Update update = new Update();
		update.set("email", "no");
		
		mongoTemplate.updateFirst(q2, update, "user");
		
		*/
		
		return mongoTemplate.find(query, User.class, "user");
	}

	public void deleteUser(String userId) {
		Criteria criteria = new Criteria("_id");
		criteria.is(userId);
		Query query = new Query(criteria);

		mongoTemplate.remove(query, "user");
	}
}