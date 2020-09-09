package ssafy.musicD.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;
import ssafy.musicD.dto.Account;
import ssafy.musicD.service.AccountServiceImpl;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
public class AccountController {
	@Autowired
	private AccountServiceImpl accountService;
	
	@ApiOperation(value="계정 목록", response=String.class)
	@GetMapping("/account/{username}")
	public List<Account> getAccountInfo(@PathVariable String username) {
		List<Account> list = accountService.getAccountInfo(username);
		
		
		return list;
	}
}
