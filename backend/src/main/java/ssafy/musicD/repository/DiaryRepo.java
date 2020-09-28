package ssafy.musicD.repository;

import java.util.List;

import ssafy.musicD.Domain.Diary;
import ssafy.musicD.dto.StrDiary;

public interface DiaryRepo {
	public List<StrDiary> findDiary(String userId, int month);
	public Boolean insertDiary(Diary diary);
	public Boolean updateDiary(Diary diary);
	public Boolean deleteDiary(String id);
}
