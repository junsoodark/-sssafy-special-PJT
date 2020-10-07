package ssafy.musicD.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;
import ssafy.musicD.Domain.Diary;
import ssafy.musicD.dto.Song;
import ssafy.musicD.service.SongService;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class SongController {
    @Autowired
    private SongService songService;

    @GetMapping("/music")
    @ApiOperation(value = "노래 검색", response = String.class)
    public List<Song> searchSong(@RequestParam String keyword) {
        List<Song> keywordSongList = songService.searchSong(keyword);
        return keywordSongList;
    }
    
    @PostMapping("/music/recommend")
    @ApiOperation(value = "노래 추천", response = String.class)
    public List<Song> recomendSong(@RequestBody Diary diary) {
    	String emotion = diary.getFeel();
    	String month = Integer.toString(diary.getMonth());
    	if (month.length() == 1) {
    		month = "0" + month;
    	}
    	
    	
    	List<Song> list = songService.recommendSong(emotion, month);
    	int[] arr = randomIndex(list.size());
    	
    	List<Song> recomendSongs = new ArrayList<Song>();
    	
    	for (int i = 0; i < arr.length; i++) {
    		recomendSongs.add(list.get(arr[i]));
    	}
    			
    	return recomendSongs;
    }
    
    public int[] randomIndex(int size) {
    	int[] arr = new int[10];
    	Random r = new Random();
    	
    	for (int i = 0; i < 10; i++) {
    		arr[i] = r.nextInt(size-1) + 1;
    		for (int j = 0; j < i; j++) {
    			if (arr[i]==arr[j])
    				i--;
    		}
    	}
    	
    	return arr;
    }
}
