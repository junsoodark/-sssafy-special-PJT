package ssafy.musicD.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

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
import ssafy.musicD.Domain.Playlist;
import ssafy.musicD.dto.Song;
import ssafy.musicD.service.PlaylistService;
import ssafy.musicD.service.SongService;

@RestController
@RequestMapping("/api/playlist")
//@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
@CrossOrigin(origins = "*")
public class PlaylistController {
	@Autowired
	private PlaylistService playlistService;
	@Autowired
	private SongService songService;
	
	// 월별 플레이리스트 조회
	@PostMapping("/month")
	@ApiOperation(value = "월별 플레이리스트 조회", response = String.class)
	public Map<String, Object> getMonthPlaylist(@RequestBody Map<String, Object> m) {
		String userId = (String)m.get("userId");
		int month = (int)m.get("month");
		int year = (int)m.get("year");
		List<Song> playlist = playlistService.getMonthPlaylist(userId, month, year);
		
		if (playlist.size() < 3 && playlist.size() > 0) {
			Song temp = playlist.get(0);
			List<Song> list = songService.similarity(temp.getGenre());
			int index = 0;
			double max = -1;
				
			for (int j = 0; j < list.size(); j++) {
				double tempSim = calSimilarity(temp, list.get(j));
				if (tempSim > max) {
					max = tempSim;
					index = j;
				}
			}
			playlist.add(list.get(index));
			
		} else {
			double max = -1;
			int index = 0;
			int num = randomIndex(playlist.size());
			Song temp = playlist.get(num);
			
			List<Song> list = songService.similarity(temp.getGenre());
			for (int j = 0; j < list.size(); j++) {
				double tempSim = calSimilarity(temp, list.get(j));
				if (tempSim > max && tempSim != 1) {
					max = tempSim;
					index = j;
				}
			}
			playlist.add(list.get(index));
		}
		
		Map<String, Object> map = new HashMap<>();
		map.put("status", 200);
		map.put("playlist", playlist);
		
		return map;
	}
	
	public double calSimilarity(Song song1, Song song2) {
		if (song1.getId().equals(song2.getId()))
				return 0;
		double similarity;
		double happy1 = song1.getHappy();
		double sad1 = song1.getSad();
		double angry1 = song1.getAngry();
		double fear1 = song1.getAngry();
		double excited1 = song1.getExcited();
		double indifferent1 = song1.getIndifferent();
		double happy2 = song2.getHappy();
		double sad2 = song2.getSad();
		double angry2 = song2.getAngry();
		double fear2 = song2.getAngry();
		double excited2 = song2.getExcited();
		double indifferent2 = song2.getIndifferent();
		
		double top = happy1*happy2 + sad1*sad2 + angry1*angry2 + fear1*fear2 + excited1*excited2 + indifferent1*indifferent2;
		double bottom = Math.sqrt(happy1*happy1 + sad1*sad1 + angry1*angry1 + fear1*fear1 + excited1*excited1 + indifferent1*indifferent1)
				* Math.sqrt(happy2*happy2 + sad2*sad2 + angry2*angry2 + fear2*fear2 + excited2*excited2 + indifferent2*indifferent2);
		
		similarity = top / bottom;
		
		return similarity;
	}
	
	public int randomIndex(int size) {
    	Random r = new Random();
    	int num = 13;

    	while(num == 13) {
    		num = r.nextInt(size);
    	}
    	
    	return num;
    }
	
	// 플레이리스트 목록 조회
	@GetMapping("/{userId}")
	@ApiOperation(value = "나만의 플레이리스트 목록 조회", response = String.class)
	public Map<String, Object> getPlaylist(@PathVariable String userId) {
		List<Playlist> playlists = playlistService.getPlaylist(userId);

		Map<String, Object> map = new HashMap<>();
		
		map.put("status", 200);
		map.put("playlists", playlists);
		
		return map;
	}
	
	// 플레이리스트 상세 조회
	@GetMapping("/detail/{playlistId}")
	@ApiOperation(value = "플레이리스트 상세 조회", response = String.class)
	public Map<String, Object> getDetailPlaylist(@PathVariable String playlistId) {
		Playlist playlist = playlistService.getDetailPlaylist(playlistId);
		Map<String, Object> map = new HashMap<>();
		map.put("status", 200);
		map.put("playlist", playlist);
		return map;
	}
	
	
	// 플레이리스트 생성
	@PostMapping
	@ApiOperation(value = "플레이리스트 생성", response = String.class)
	public Map<String, Object> createPlaylist(@RequestBody Map<String, String> m) {
		String userId = m.get("userId");
		String title = m.get("title");
		playlistService.createPlaylist(userId, title);
		Map<String, Object> map = new HashMap<>();
		map.put("status", 200);
		return map;
	}
	
	// 플레이리스트 수정
	@PutMapping
	@ApiOperation(value = "플레이리스트 수정", response = String.class)
	public Map<String, Object> updatePlaylist(@RequestBody Playlist playlist) {
		String playlistId = playlist.getId();
		String title = playlist.getTitle();
		playlistService.updatePlaylist(playlistId, title);
		Map<String, Object> map = new HashMap<>();
		map.put("status", 200);
		return map;
	}
	
	// 플레이리스트 삭제
	@DeleteMapping("/{playlistId}")
	@ApiOperation(value = "플레이리스트 삭제", response = String.class)
	public Map<String, Object> deletePlaylist(@PathVariable String playlistId) {
		playlistService.deletePlaylist(playlistId);
		Map<String, Object> map = new HashMap<>();
		map.put("status", 200);
		return map;
	}
	
	// 플레이리스트 노래 추가
	@PostMapping("/song")
	@ApiOperation(value = "플레이리스트 노래 추가", response = String.class)
	public Map<String, Object> addPlaylistSong(@RequestBody Map<String, String> m) {
		String playlistId = m.get("playlistId");
		String songId = m.get("songId");
		playlistService.insertSong(playlistId, songId);
		Map<String, Object> map = new HashMap<>();
		map.put("status", 200);
		return map;
	}
	
	// 플레이리스트 노래 삭제
	@PostMapping("/deletesong")
	@ApiOperation(value = "플레이리스트 노래 삭제", response = String.class)
	public Map<String, Object> deletePlaylistSong(@RequestBody Map<String, String> m) {
		String playlistId = m.get("playlistId");
		String songId = m.get("songId");
		playlistService.deleteSong(playlistId, songId);
		Map<String, Object> map = new HashMap<>();
		map.put("status", 200);
		return map;
	}

	
}
