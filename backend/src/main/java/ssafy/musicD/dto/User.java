package ssafy.musicD.dto;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection="user")
public class User {
	@Id
	private String _id;
	private String email;
	private String password;
	private String name;
	private String nickname;
	private String profileURL;
	private int[] playlist;
	private int[] diary;
	private List<String> friends;
	private List<String> waitFriends;

}
