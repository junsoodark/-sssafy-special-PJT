package ssafy.musicD.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ssafy.musicD.Domain.Playlist;
import ssafy.musicD.dto.Song;
import ssafy.musicD.repository.PlaylistRepo;

@Service
public class PlaylistServiceImpl implements PlaylistService {
	@Autowired
	private PlaylistRepo playlistRepo;

	@Override
	public Playlist getDetailPlaylist(String playlistId) {
		return playlistRepo.findPlaylist(playlistId);
	}

	@Override
	public List<Playlist> getPlaylist(String userId) {
		return playlistRepo.findAllPlaylist(userId);
	}

	@Override
	public void createPlaylist(String userId, String title) {
		playlistRepo.createPlaylist(userId, title);
	}

	@Override
	public void updatePlaylist(String playlistId, String title) {
		playlistRepo.updatePlaylist(playlistId, title);
	}
	
	@Override
	public void deletePlaylist(String playlistId) {
		playlistRepo.deletePlaylist(playlistId);
	}

	@Override
	public void insertSong(String playlistId, Song song) {
		playlistRepo.insertSong(playlistId, song);
	}
	
	@Override
	public void deleteSong(String playlistId, String songId) {
		playlistRepo.deleteSong(playlistId, songId);
	}

}
