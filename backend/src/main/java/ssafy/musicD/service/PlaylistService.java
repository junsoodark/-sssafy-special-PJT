package ssafy.musicD.service;

import java.util.List;

import ssafy.musicD.Domain.Playlist;
import ssafy.musicD.dto.Song;

public interface PlaylistService {
	public Playlist getDetailPlaylist(String playlistId);
	public List<Playlist> getPlaylist(String userId);
	public void createPlaylist(String userId, String title);
	public void updatePlaylist(String playlistId, String title);
	public void deletePlaylist(String playlistId);
	public void insertSong(String playlistId, Song song);
	public void deleteSong(String playlistId, String songId);
}
	