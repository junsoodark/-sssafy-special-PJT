package ssafy.musicD.dto;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Document(collection="user")
public class User {
	@Id
	private String id;
	private String email;
	private String password;
	private String name;
	private Date regdate;
	private String profileURL;
	private int[] playlist;
	private int[] diary;
	private int[] friends;
	private String role;
	
	@Builder
	public User(String email, String name, String password) {
		this.email = email;
		this.name = name;
		this.password = password;
	}
}
