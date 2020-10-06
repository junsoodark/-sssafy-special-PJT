package ssafy.musicD.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ssafy.musicD.Domain.Diary;
import ssafy.musicD.dto.Song;
import ssafy.musicD.dto.DiaryDto;
import ssafy.musicD.repository.DiaryRepo;
import ssafy.musicD.repository.SongRepo;

@Service
public class DiaryServiceImpl implements DiaryService {
	@Autowired
	private DiaryRepo diaryRepo;
	@Autowired
	private SongRepo songRepo;

	@Override
	public void insertSong(String diaryId, String songId) {
		Song song = songRepo.findById(songId).orElse(null);
		diaryRepo.insertSong(diaryId, song);
	}

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
	public List<DiaryDto> findDiaryByMonth(String userId, int month, int year) {
		return diaryRepo.findDiary(userId, month, year);
	}
}
