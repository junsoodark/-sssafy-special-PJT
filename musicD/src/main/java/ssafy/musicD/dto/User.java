package ssafy.musicD.dto;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

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
	public String get_id() {
		return _id;
	}
	public void set_id(String _id) {
		this._id = _id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getProfileURL() {
		return profileURL;
	}
	public void setProfileURL(String profileURL) {
		this.profileURL = profileURL;
	}
	public int[] getPlaylist() {
		return playlist;
	}
	public void setPlaylist(int[] playlist) {
		this.playlist = playlist;
	}
	public int[] getDiary() {
		return diary;
	}
	public void setDiary(int[] diary) {
		this.diary = diary;
	}
	public int[] getFriends() {
		return friends;
	}
	public void setFriends(int[] friends) {
		this.friends = friends;
	}
	
}
