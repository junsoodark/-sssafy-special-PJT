package com.musicD.dto;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection="musicD")
public class UserDto {
	private String _id;
	private String email;
	private String name;
	private String profileURL;
	private int[] playlist;
	private int[] diary;
	private int[] friends;
}
