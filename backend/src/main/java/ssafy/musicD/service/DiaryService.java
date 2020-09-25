package ssafy.musicD.service;

import java.util.List;

import ssafy.musicD.dto.Diary;

public interface DiaryService {
	public List<Diary> findDiaryByMonth(String userId, int month);
	public boolean insertDiary(Diary diary);
	public boolean updateDiary(Diary diary);
	public boolean deleteDiary(String id);
}
