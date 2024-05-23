package com.kodnest.musicmuse.serviceimpl;

import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kodnest.musicmuse.entity.Playlist;
import com.kodnest.musicmuse.entity.Song;
import com.kodnest.musicmuse.repository.PlaylistRepository;
import com.kodnest.musicmuse.service.PlaylistService;

@Service
public class PlaylistServiceImpl implements PlaylistService{
	@Autowired
	PlaylistRepository playlistrepository;
	
	@Override
	public void addPlaylist(Playlist playlist) {
		Playlist  exiplaylist = playlistrepository.findByName(playlist.getName());
if(exiplaylist==null) {
		playlistrepository.save(playlist);
		
	}
else {
	System.out.println("playlist exist");
}
	}

	@Override
	public List<Playlist> fetchAllPlaylists() {
		List<Playlist>songs=playlistrepository.findAll();
		return songs;
	}

}
