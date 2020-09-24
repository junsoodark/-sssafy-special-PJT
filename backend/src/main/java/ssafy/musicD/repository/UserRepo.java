package ssafy.musicD.repository;

import java.util.Optional;

//	public void signUp(User user);
//	public void updateUserInfo(User user);
//	public void deleteUser(String userId);
//	public String checkEmail(String email);
//	public String checkNickname(String nickname);
import org.springframework.data.mongodb.repository.MongoRepository;

import ssafy.musicD.Domain.Member;

public interface UserRepo extends MongoRepository<Member, String> {
	Member findByEmail(String email);
	
//	Optional<Member> findById(String userId);
	
	Boolean existsByEmail(String email);
	
	Boolean existsByNickName(String nickName);
	
	Long deleteById(Long id);
	
}
