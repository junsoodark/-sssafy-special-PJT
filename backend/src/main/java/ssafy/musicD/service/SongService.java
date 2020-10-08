package ssafy.musicD.service;

import ssafy.musicD.dto.Song;

import java.util.List;

public interface SongService {

    public List<Song> searchSong(String keyword);
    public List<Song> recommendSong(String emotion, String genre);
    public List<Song> similarity(String genre);
}
