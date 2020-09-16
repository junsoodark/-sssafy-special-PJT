package ssafy.musicD.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.annotations.ApiOperation;
import ssafy.musicD.dto.User;
import ssafy.musicD.service.UserServiceImpl;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
public class UserController {
	@Autowired
	private UserServiceImpl userService;
	
	@ApiOperation(value="회원 정보 조회", response=String.class)
	@GetMapping("/user/{userId}")
	public User getAccountInfo(@PathVariable String userId) {
		User user = userService.getUserInfo(userId);

		return user;
	}

	@ApiOperation(value="계정 탈퇴", response=String.class)
	@DeleteMapping("/user/{userId}")
	public ResponseEntity<Map<String, Object>> delete(@PathVariable String userId) {
		ResponseEntity<Map<String, Object>> entity = null;
		try {
			userService.deleteUser(userId);
			entity = handleSuccess(userId + "번 회원 삭제");
		} catch (RuntimeException e) {
			entity = handleException(e);
		}

		return entity;
	}

	/* 예외 처리 */
	private ResponseEntity<Map<String, Object>> handleSuccess(Object data) {
		Map<String, Object> resultMap = new HashMap<>();
		resultMap.put("status", true);
		resultMap.put("data", data);
		return new ResponseEntity<Map<String, Object>>(resultMap, HttpStatus.OK);
	}

	private ResponseEntity<Map<String, Object>> handleException(Exception e) {
		Map<String, Object> resultMap = new HashMap<>();
		resultMap.put("status", false);
		resultMap.put("data", e.getMessage());
		return new ResponseEntity<Map<String, Object>>(resultMap, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}