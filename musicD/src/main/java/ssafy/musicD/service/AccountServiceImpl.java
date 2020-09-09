package ssafy.musicD.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import ssafy.musicD.dto.Account;

@Service
public class AccountServiceImpl implements AccountService {
	@Autowired
	private MongoTemplate mongoTemplate;
	
	public List<Account> getAccountInfo(String username) {
		System.out.println("*********************");
		
		Query query = new Query(new Criteria().andOperator(
				Criteria.where("username").is(username)
		));
		
		return mongoTemplate.find(query, Account.class, "accounts");
	}
}
