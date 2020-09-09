package ssafy.musicD.dto;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="accounts")
public class Account {
	@Id
	private String _id;
	private String username;
	public String getId() {
		return _id;
	}
	public void setId(String id) {
		this._id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
}
