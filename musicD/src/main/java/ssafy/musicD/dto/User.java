package ssafy.musicD.dto;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Document(collection="user")
public class User {
	@Id
	private String _id;
	private String email;
	private String password;
	private String name;
	private Date regdate;
	private String profileURL;
	private int[] playlist;
	private int[] diary;
	private int[] friends;
	
	@Builder
	public User(String email, String name, String password) {
		this.email = email;
		this.name = name;
		this.password = password;
	}
}
