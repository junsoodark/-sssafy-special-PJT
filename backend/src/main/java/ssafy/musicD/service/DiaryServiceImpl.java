package ssafy.musicD.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ssafy.musicD.Domain.Diary;
import ssafy.musicD.dto.StrDiary;
import ssafy.musicD.repository.DiaryRepo;

@Service
public class DiaryServiceImpl implements DiaryService {
	@Autowired
	private DiaryRepo diaryRepo;

	@Override
	public boolean insertDiary(Diary diary) {
		diaryRepo.insertDiary(diary);
		return false;
	}

	@Override
	public boolean updateDiary(Diary diary) {
		diaryRepo.updateDiary(diary);
		return false;
	}

	@Override
	public boolean deleteDiary(String id) {
		diaryRepo.deleteDiary(id);
		return false;
	}

	@Override
	public List<StrDiary> findDiaryByMonth(String userId, int month) {
		return diaryRepo.findDiary(userId, month);
	}
}
