package ssafy.musicD.models;

import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.Email;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
@Document(collection = "user")
public class User {
	@Id
	private String _id;

	@Email
	private String email;
	private String password;
	private String name;
//	private Date regdate;
	private String profileURL;
	private int[] playlist;
	private int[] diary;
	private int[] friends;
	@DBRef
	private Set<Role> roles = new HashSet<>();

	@Builder
	public User(String email, String name, String password) {
		this.email = email;
		this.name = name;
		this.password = password;
	}
}
