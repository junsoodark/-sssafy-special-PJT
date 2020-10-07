package ssafy.musicD.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import ssafy.musicD.dto.Song;

@Repository
public class SongRepoImpl implements SongRepo2 {
	@Autowired
	private MongoTemplate mongoTemplate;

	@Override
	public List<Song> searchSong(String keyword) {
		Query query = new Query(Criteria.where("song_name").regex(keyword));
		System.out.println(keyword);
		List<Song> keywordSong = mongoTemplate.find(query, Song.class, "emotion");
		return keywordSong;
	}
	
	public List<Song> recommendSong(String emotion, String month) {
		Query query = new Query(Criteria.where(emotion).gt(0.9)).addCriteria(Criteria.where("issue_date").regex(month+"$")).with(Sort.by(Sort.Direction.DESC, emotion));
		query.limit(50);
		
		List<Song> list = mongoTemplate.find(query, Song.class, "emotion");
		
		return list;
	}
}
