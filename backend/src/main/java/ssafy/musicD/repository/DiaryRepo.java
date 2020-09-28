package ssafy.musicD.repository;

import java.util.List;

import ssafy.musicD.dto.Diary;

public interface DiaryRepo {
	public List<Diary> findDiary(String userId, int month);
	public Boolean insertDiary(Diary diary);
	public Boolean updateDiary(Diary diary);
	public Boolean deleteDiary(String id);
}
