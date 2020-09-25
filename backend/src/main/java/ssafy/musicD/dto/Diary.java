package ssafy.musicD.dto;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;
@Data
@Document(collection="diary")
public class Diary {
	@Id
	private ObjectId id;
	private ObjectId userId;
	private int year;
	private int month;
	private int day;
	private String date;
	private String feel;
	private String weather;
	private Song song;
	private Boolean show;
	private String img;
	private String context;
	
//	@Builder
//	public User(String email, String name, String password) {
//		this.email = email;
//		this.name = name;
//		this.password = password;
//	}
}
