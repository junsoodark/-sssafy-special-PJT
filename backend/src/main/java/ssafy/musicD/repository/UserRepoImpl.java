// package ssafy.musicD.repository;

// import java.util.List;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.data.mongodb.core.MongoTemplate;
// import org.springframework.data.mongodb.core.query.Criteria;
// import org.springframework.data.mongodb.core.query.Query;
// import org.springframework.data.mongodb.core.query.Update;
// import org.springframework.stereotype.Repository;

// import ssafy.musicD.dto.User;

// @Repository
// public class UserRepoImpl implements UserRepo {
// 	@Autowired
// 	private MongoTemplate mongoTemplate;

// 	public void signUp(User user) {
// 		mongoTemplate.insert(user, "user");
// 	}
	
// 	public User getUserInfo(String userId) {
// 		Criteria criteria = new Criteria("_id");
// 		criteria.is(userId);
// 		Query query = new Query(criteria);

// 		return mongoTemplate.findOne(query, User.class , "user");

// 	}

// 	public void updateUserInfo(User user) {
// 		Criteria criteria = new Criteria("_id");
// 		criteria.is(user.get_id());
// 		Query query = new Query(criteria);

// 		Update update = new Update();
// 		update.set("nickname", user.getNickname());
// 		update.set("profileURL", user.getProfileURL());
// 		update.set("password", user.getPassword());

// 		mongoTemplate.updateFirst(query, update, "user");
// 	}

// 	public void deleteUser(String userId) {
// 		Criteria criteria = new Criteria("_id");
// 		criteria.is(userId);
// 		Query query = new Query(criteria);

// 		mongoTemplate.remove(query, "user");
// 	}

// 	public String checkEmail(String email) {
// 		Criteria criteria = new Criteria("email");
// 		criteria.is(email);
// 		Query query = new Query(criteria);

// 		User temp = mongoTemplate.findOne(query, User.class, "user");
// 		if (temp == null) {
// 			return "SUCCESS";
// 		} else
// 			return "FAIL";
// 	}

// 	public String checkNickname(String nickname) {
// 		Criteria criteria = new Criteria("nickname");
// 		criteria.is(nickname);
// 		Query query = new Query(criteria);

// 		User temp = mongoTemplate.findOne(query, User.class, "user");
// 		if (temp == null) {
// 			return "SUCCESS";
// 		} else
// 			return "FAIL";
// 	}
// }