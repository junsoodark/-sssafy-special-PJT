package ssafy.musicD.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ssafy.musicD.dto.Song;
import ssafy.musicD.repository.SongRepo2;
import ssafy.musicD.youtube.Youtube;

import java.util.ArrayList;
import java.util.List;

@Service
public class SongServiceImpl implements SongService{
    @Autowired
    private SongRepo2 songRepo;
	@Autowired
	private Youtube youtube;


    public List<Song> searchSong(String keyword){
    	List<Song> songs = songRepo.searchSong(keyword);
    	List<Song> result = new ArrayList();
    	for(Song song: songs) {
    		String search = song.getArtist()+" "+song.getSong_name();
			String videoId = youtube.getVideoId(search);
			song.setYoutubeId(videoId);
			result.add(song);
    	}
    	return result;
    }
    
    public List<Song> recommendSong(String emotion, String genre) {
    	List<Song> songs = songRepo.recommendSong(emotion, genre);
    	List<Song> result = new ArrayList();
    	for(Song song: songs) {
    		String search = song.getArtist()+" "+song.getSong_name();
			String videoId = youtube.getVideoId(search);
			song.setYoutubeId(videoId);
			result.add(song);
    	}
    	return result;
    }
}
