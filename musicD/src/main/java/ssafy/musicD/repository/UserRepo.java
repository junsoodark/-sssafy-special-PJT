package ssafy.musicD.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import ssafy.musicD.Domain.Member;

public interface UserRepo extends MongoRepository<Member, String> {
	Member findByEmail(String email);
	
	Boolean existsByEmail(String email);
	
	Boolean existsByNickName(String nickName);
	
	Long deleteById(Long id);
}
