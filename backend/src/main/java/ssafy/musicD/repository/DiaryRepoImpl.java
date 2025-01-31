package ssafy.musicD.repository;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import com.mongodb.BasicDBObject;

import ssafy.musicD.Domain.Member;
import ssafy.musicD.dto.Diary;

@Repository
public class DiaryRepoImpl implements DiaryRepo {
	@Autowired
	private MongoTemplate mongoTemplate;

	@Override
	public List<Diary> findDiary(String userId, int month) {
		Query query = new Query();
		query.addCriteria(new Criteria().andOperator(
				Criteria.where("userId").is(new ObjectId(userId)),
				Criteria.where("month").is(month)
				));
		List<Diary> result = mongoTemplate.find(query, Diary.class);

		for (Diary diary : result) {
			System.out.println("result - " + diary);
		}

		return result;
	}

	@Override
	public Boolean insertDiary(Diary diary) {
		diary.setUserId(diary.getUserId());
		mongoTemplate.save(diary);
		return true;
	}

	@Override
	public Boolean deleteDiary(String id) {
		Query query = new Query();
		query.addCriteria(Criteria.where("id").is(new ObjectId(id)));
		mongoTemplate.remove(query, Diary.class);
		return true;
	}

	@Override
	public Boolean updateDiary(Diary diary) {
		Query query = new Query();
		query.addCriteria(Criteria.where("id").is(diary.getId()));
		Update update = new Update();
        update.set("feel", diary.getFeel());
        update.set("weather", diary.getWeather());
        update.set("song", diary.getSong());
        update.set("show", diary.getShow());
        update.set("img", diary.getImg());
        update.set("context", diary.getContext());

		mongoTemplate.updateFirst(query, update, Diary.class);
		return true;
	}
}