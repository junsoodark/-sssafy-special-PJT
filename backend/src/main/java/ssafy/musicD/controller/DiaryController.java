package ssafy.musicD.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;
import ssafy.musicD.dto.Diary;
import ssafy.musicD.service.DiaryService;

@RestController
@RequestMapping("/api/diary")
@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
public class DiaryController {
	@Autowired
	private DiaryService diaryService;
	
	// 일기 전체 조회 (월별)
	@GetMapping
	@ApiOperation(value = "일기 전체 조회 (월별)", response = String.class)
	public Map<String, Object> searchAll(@RequestBody Map<String, Object> m) {
		Map<String, Object> map = new HashMap<>();
		String userId = (String)m.get("userId");
		int month = (int)m.get("month");
		System.out.println(userId);
		System.out.println(month);
		try {
			List<Diary> diarys = diaryService.findDiaryByMonth(userId, month);
			map.put("status", 200);
			map.put("diarys", diarys);
		} catch (Exception e) {
			map.put("status", 500);
		}
		return map;
	}
	
	// 일기 삭제
	@DeleteMapping
	@ApiOperation(value = "일기 삭제", response = String.class)
	public Map<String, Object> deleteDiary(@RequestBody Map<String, Object> m) {
		String diaryId = (String)m.get("diaryId");
		diaryService.deleteDiary(diaryId);
		Map<String, Object> map = new HashMap<>();
		map.put("status", 200);
		return map;
	}
	
	// 일기 등록
	@PostMapping
	@ApiOperation(value = "일기 등록", response = String.class)
	public Map<String, Object> registerDiary(@RequestBody Diary diary) {
		diaryService.insertDiary(diary);
		Map<String, Object> map = new HashMap<>();
		map.put("status", 200);
		return map;
	}
	
	// 일기 수정
	@PutMapping
	@ApiOperation(value = "일기 수정", response = String.class)
	public Map<String, Object> updateDiary(@RequestBody Diary diary) {
		diaryService.updateDiary(diary);
		Map<String, Object> map = new HashMap<>();
		map.put("status", 200);
		return map;
	}
}
