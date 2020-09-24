package ssafy.musicD.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection="user")
public class User {
	@Id
	private String _id;
	private String email;
	private String name;
	private String profileURL;
	private int[] playlist;
	private int[] diary;
	private int[] friends;
	private int[] friends_wait;
}
