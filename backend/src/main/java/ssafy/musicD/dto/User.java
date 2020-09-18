package ssafy.musicD.dto;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
	private int[] friends;

}
