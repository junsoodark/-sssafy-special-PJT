package ssafy.musicD.controller;

//	@ApiOperation(value="회원 가입", response=String.class)
//	@PostMapping("/user/signup")
//	public ResponseEntity<Map<String, Object>> signUp(@RequestBody User user) {
//		ResponseEntity<Map<String, Object>> entity = null;
//		try {
//			userService.signUp(user);
//			entity = handleSuccess(user.getNickname() + "님 회원 가입");
//		} catch (Exception e) {
//			entity = handleException(e);
//		}
//
//		return entity;
//	}
//	
//	@ApiOperation(value="회원 정보 조회", response=String.class)
//	@GetMapping("/user/{userId}")
//	public User getAccountInfo(@PathVariable String userId) {
//		User user = userService.getUserInfo(userId);
//
//		return user;
//	}
//
//	@ApiOperation(value="회원 정보 수정", response=String.class)
//	@PutMapping("/user")
//	public ResponseEntity<Map<String, Object>> updateUser(@RequestBody User user) {
//		ResponseEntity<Map<String, Object>> entity = null;
//		try {
//			userService.updateUserInfo(user);
//			entity = handleSuccess(user.get_id() + "번 회원 정보 수정");;
//		} catch (Exception e) {
//			entity = handleException(e);
//		}
//
//		return entity;
//	}
//
//	@ApiOperation(value="계정 탈퇴", response=String.class)
//	@DeleteMapping("/user/{userId}")
//	public ResponseEntity<Map<String, Object>> delete(@PathVariable String userId) {
//		ResponseEntity<Map<String, Object>> entity = null;
//		try {
//			userService.deleteUser(userId);
//			entity = handleSuccess(userId + "번 회원 삭제");
//		} catch (RuntimeException e) {
//			entity = handleException(e);
//		}
//
//		return entity;
//	}
//
//	/* 예외 처리 */
//	private ResponseEntity<Map<String, Object>> handleSuccess(Object data) {
//		Map<String, Object> resultMap = new HashMap<>();
//		resultMap.put("status", true);
//		resultMap.put("data", data);
//		return new ResponseEntity<Map<String, Object>>(resultMap, HttpStatus.OK);
//	}
//
//	private ResponseEntity<Map<String, Object>> handleException(Exception e) {
//		Map<String, Object> resultMap = new HashMap<>();
//		resultMap.put("status", false);
//		resultMap.put("data", e.getMessage());
//		return new ResponseEntity<Map<String, Object>>(resultMap, HttpStatus.INTERNAL_SERVER_ERROR);
//	}

import io.jsonwebtoken.ExpiredJwtException;
import io.swagger.annotations.ApiOperation;
import ssafy.musicD.Domain.Member;
import ssafy.musicD.Domain.Token;
import ssafy.musicD.dto.Diary;
import ssafy.musicD.dto.Song;
import ssafy.musicD.jwt.JwtUtils;
import ssafy.musicD.repository.UserRepo;
import ssafy.musicD.service.UserDetailsServiceImpl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
public class UserController {
	private Logger logger = LoggerFactory.getLogger(ApplicationRunner.class);
	@Autowired
	private UserRepo userRepo;
	@Autowired
	RedisTemplate<String, Object> redisTemplate;
	@Autowired
	private UserDetailsServiceImpl userDetailsServiceImpl;
	@Autowired
	private JwtUtils jwtUtils;
	@Autowired
	private AuthenticationManager am;
	@Autowired
	private PasswordEncoder bcryptEncoder;

//    @GetMapping(path="/normal")
//    public ResponseEntity<?> onlyNormal() {
//        return new ResponseEntity(HttpStatus.OK);
//    }

	// 로그아웃
	@PostMapping(path = "/account/logout")
	@ApiOperation(value = "로그아웃", response = String.class)
	public ResponseEntity<?> logout(@RequestBody Map<String, String> m) {
		String email = null;
		String accessToken = m.get("accessToken");
		try {
			email = jwtUtils.getEmailFromToken(accessToken);
		} catch (IllegalArgumentException e) {
		} catch (ExpiredJwtException e) { // expire됐을 때
			email = e.getClaims().getSubject();
			logger.info("username from expired access token: " + email);
		}

		try {
			if (redisTemplate.opsForValue().get(email) != null) {
				// delete refresh token
				redisTemplate.delete(email);
			}
		} catch (IllegalArgumentException e) {
			logger.warn("user does not exist");
		}

		// cache logout token for 10 minutes!
		logger.info(" logout ing : " + accessToken);
		redisTemplate.opsForValue().set(accessToken, true);
		redisTemplate.expire(accessToken, 10 * 6 * 1000, TimeUnit.MILLISECONDS);

		return new ResponseEntity(HttpStatus.OK);
	}

	// 로그인
	@PostMapping(path = "/account/login")
	@ApiOperation(value = "로그인", response = String.class)
	public Map<String, Object> login(@RequestBody Map<String, String> m) throws Exception {
		final String email = m.get("email");
		logger.info("input email: " + email);
		try {
			am.authenticate(new UsernamePasswordAuthenticationToken(email, m.get("password")));
		} catch (Exception e) {
			throw e;
		}

		final UserDetails userDetails = userDetailsServiceImpl.loadUserByUsername(email);
		final String accessToken = jwtUtils.generateAccessToken(userDetails);
		final String refreshToken = jwtUtils.generateRefreshToken(email);

		Token retok = new Token();
		retok.setEmail(email);
		retok.setRefreshToken(refreshToken);

		// generate Token and save in redis
		ValueOperations<String, Object> vop = redisTemplate.opsForValue();
		vop.set(email, retok);

		logger.info("generated access token: " + accessToken);
		logger.info("generated refresh token: " + refreshToken);
		Map<String, Object> map = new HashMap<>();
		map.put("status", 200);
		map.put("accessToken", accessToken);
		map.put("refreshToken", refreshToken);
		map.put("userId", userRepo.findByEmail(email).getId().toString());
		return map;
	}

	// 회원가입
	@PostMapping(path = "/account/signup")
	@ApiOperation(value = "회원 가입", response = String.class)
	public Map<String, Object> addNewUser(@RequestBody Member user) {
		String email = user.getEmail();
		Map<String, Object> map = new HashMap<>();
		if (userRepo.findByEmail(email) == null) {
			user.setEmail(email);
			if (user.getNickname().equals("admin")) {
				user.setRole("ROLE_ADMIN");
			} else {
				user.setRole("ROLE_USER");
			}
			user.setPassword(bcryptEncoder.encode(user.getPassword()));
			map.put("status", 200);
			map.put("email", user.getEmail());
			map.put("nickname", user.getNickname());
			user.setFriends(new ArrayList<Long>());
			userRepo.save(user);
			return map;
		} else {
			map.put("status", 500);
			map.put("message", "duplicated email");
		}
		return map;
	}

	// 이메일 중복 확인
	@PostMapping(path = "/account/checkemail")
	@ApiOperation(value = "이메일 중복 확인", response = String.class)
	public boolean checkEmail(@RequestBody Map<String, String> m) {
		System.out.println("이메일 중복 체크: " + m.get("email"));
		return userRepo.existsByEmail(m.get("email"));
	}

	// 닉네임 중복 확인
	@PostMapping(path = "/account/checknickname")
	@ApiOperation(value = "닉네임 중복 확인", response = String.class)
	public boolean checkNickname(@RequestBody Map<String, String> m) {
		System.out.println("닉네임 중복 체크: " + m.get("nickname"));
		return userRepo.existsByNickname(m.get("nickname"));
	}

	// 회원 정보 조회
	@GetMapping(path = "/account/{userId}")
	@ApiOperation(value = "회원 정보 조회", response = String.class)
	public Map<String, Object> getUser(@PathVariable String userId) {
		Map<String, Object> map = new HashMap<>();
		Member member = userRepo.findById(userId).orElse(null);
		System.out.println(member);
		if (member != null) {
			member.setPassword("");
			map.put("success", true);
			map.put("member", member);			
			return map;
		} else {
			map.put("success", false);
			map.put("message", "can't find member");
		}
		return map;
	}

	// 회원정보수정
	@PutMapping(path = "/account/")
	@ApiOperation(value = "회원정보수정", response = String.class)
	public Map<String, Object> modifyUser(@RequestBody Member user) {
		String email = user.getEmail();
		Map<String, Object> map = new HashMap<>();
		if (userRepo.findByEmail(email) != null) {
			userRepo.save(user);
			map.put("status", 200);
			return map;
		} else {
			map.put("status", 500);
			map.put("message", "duplicated email");
		}
		return map;
	}

	// 회원정보삭제
	@DeleteMapping(path = "/account/{userId}")
	@ApiOperation(value = "회원 탈퇴", response = String.class)
	public void deleteUser(@PathVariable Long userId) {
		logger.info("delete userId: " + userId);
		Long result = userRepo.deleteById(userId);
		logger.info("delete result: " + result);
	}

	// accesstoken 재발급(refresh token이 만료 안된 경우)
	@PostMapping(path = "/account/refresh")
	@ApiOperation(value = "재발급", response = String.class)
	public Map<String, Object> requestForNewAccessToken(@RequestBody Map<String, String> m) {
		String accessToken = null;
		String refreshToken = null;
		String refreshTokenFromDb = null;
		String email = null;
		Map<String, Object> map = new HashMap<>();
		try {
			accessToken = m.get("accessToken");
			refreshToken = m.get("refreshToken");
			logger.info("access token in rnat: " + accessToken);
			try {
				email = jwtUtils.getEmailFromToken(accessToken);
			} catch (IllegalArgumentException e) {

			} catch (ExpiredJwtException e) { // expire됐을 때
				email = e.getClaims().getSubject();
				logger.info("email from expired access token: " + email);
			}

			if (refreshToken != null) { // refresh를 같이 보냈으면.
				try {
					ValueOperations<String, Object> vop = redisTemplate.opsForValue();
					Token result = (Token) vop.get(email);
					refreshTokenFromDb = result.getRefreshToken();
					logger.info("rtfrom db: " + refreshTokenFromDb);
				} catch (IllegalArgumentException e) {
					logger.warn("illegal argument!!");
				}
				// 둘이 일치하고 만료도 안됐으면 재발급 해주기.
				if (refreshToken.equals(refreshTokenFromDb) && !jwtUtils.isTokenExpired(refreshToken)) {
					final UserDetails userDetails = userDetailsServiceImpl.loadUserByUsername(email);
					String newtok = jwtUtils.generateAccessToken(userDetails);
					map.put("success", true);
					map.put("accessToken", newtok);
				} else {
					map.put("success", false);
					map.put("msg", "refresh token is expired.");
				}
			} else { // refresh token이 없으면
				map.put("success", false);
				map.put("msg", "your refresh token does not exist.");
			}

		} catch (Exception e) {
			throw e;
		}
		logger.info("m: " + m);

		return map;
	}

	// 비밀번호 찾기 이메일 인증

	// 회원가입 이메일 인증

//	@PostMapping(path = "/check")
//	public Map<String, Object> checker(@RequestBody Map<String, String> m) {
//		String email = null;
//		Map<String, Object> map = new HashMap<>();
//		try {
//			email = jwtUtils.getEmailFromToken(m.get("accessToken"));
//		} catch (IllegalArgumentException e) {
//			logger.warn("Unable to get JWT Token");
//		} catch (ExpiredJwtException e) {
//		}
//
//		if (email != null) {
//			map.put("success", true);
//			map.put("email", email);
//		} else {
//			map.put("success", false);
//		}
//		return map;
//	}
}