package ssafy.musicD.dto;

import lombok.Data;

@Data
public class Song {
	private String songId;
	private String albumCover;
	private String title;
	private String singer;
	private String youtube;
//	@Builder
//	public User(String email, String name, String password) {
//		this.email = email;
//		this.name = name;
//		this.password = password;
//	}
}
