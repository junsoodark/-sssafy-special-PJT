//package ssafy.musicD.service;
//
//import java.util.List;
//import java.util.Map;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import ssafy.musicD.dto.User;
//import ssafy.musicD.repository.UserRepoImpl;
//
//@Service
//public class UserServiceImpl implements UserService {
//	
//	@Autowired
//	UserRepoImpl userRepo;
//	
//	public List<User> getUserInfo(String name) {
//		return userRepo.getUserInfo(name);
//	}
////	
////	public String createToken(Map<String, String> data) {
////        User member = memberRepo.findByEmail(data.getEmail())  // email로 등록된 회원을 찾는다.
////                .orElseThrow(NotExistEmailException::new);
////
////        if (!member.checkPassword(loginRequest.getPassword())) {  // 유저가 보유한 패스워드와 입력받은 패스워드가 일치하는 지 확인한다.
////            throw new WrongPasswordException();
////        }
////
////        return jwtTokenProvider.createToken(loginRequest.getEmail()); // email 정보만 가지고 token을 만든다.
////    }
//}
