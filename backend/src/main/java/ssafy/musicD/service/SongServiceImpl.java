package ssafy.musicD.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ssafy.musicD.dto.Song;
import ssafy.musicD.repository.SongRepo;

import java.util.List;

@Service
public class SongServiceImpl implements SongService{
    @Autowired
    private SongRepo songRepo;

    public List<Song> searchSong(String keyword){return songRepo.searchSong(keyword); }
}
