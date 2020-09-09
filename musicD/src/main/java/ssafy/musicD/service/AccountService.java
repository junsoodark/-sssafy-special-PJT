package ssafy.musicD.service;

import java.util.List;

import ssafy.musicD.dto.Account;

public interface AccountService {
	public List<Account> getAccountInfo(String username);
}
