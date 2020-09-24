//package ssafy.musicD.service;
//
//import java.util.List;
//import java.util.Optional;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import ssafy.musicD.Domain.Member;
//import ssafy.musicD.repository.UserRepo;
//
//@Service
//public class UserServiceImpl implements UserService {
//	
//	@Autowired
//	private UserRepo userRepo;
//
//	@Override
//	public Optional<Member> findById(String userId) {
//	    return userRepo.findById(userId);
//	}
//	
//	public void signUp(Member user) {
////		userRepo.signUp(user);
//	}
//
//	public void updateUserInfo(Member user) {
////		userRepo.updateUserInfo(user);
//	}
//
//	public void deleteUser(String userId) {
////		userRepo.deleteUser(userId);
//	}
//
////	public String checkEmail(String email) {
////		return userRepo.checkEmail(email);
////	}
////
////	public String checkNickname(String nickname) {
////		return userRepo.checkNickname(nickname);
////	}
//}
