package ssafy.musicD.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ssafy.musicD.dto.Song;
import ssafy.musicD.repository.SongRepo2;

@Service
public class SongServiceImpl implements SongService{
    @Autowired
    private SongRepo2 songRepo;

    public List<Song> searchSong(String keyword){return songRepo.searchSong(keyword); }
    
    public List<Song> recommendSong(String emotion, String genre) {
    	return songRepo.recommendSong(emotion, genre);
    }
    
    public List<Song> similarity(String genre) {
    	return songRepo.similarity(genre);
    }
}
