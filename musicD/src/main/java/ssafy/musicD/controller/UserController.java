package ssafy.musicD.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;
import ssafy.musicD.dto.User;
import ssafy.musicD.service.UserServiceImpl;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
public class UserController {
	@Autowired
	private UserServiceImpl userService;
	
	@ApiOperation(value="계정 목록", response=String.class)
	@GetMapping("/user/{name}")
	public List<User> getAccountInfo(@PathVariable String name) {
		List<User> list = userService.getUserInfo(name);
		
		return list;
	}
	
	
}