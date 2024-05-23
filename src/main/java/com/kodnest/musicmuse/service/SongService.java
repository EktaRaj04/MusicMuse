package com.kodnest.musicmuse.service;

import java.util.List;


import org.springframework.stereotype.Service;

import com.kodnest.musicmuse.entity.Playlist;
import com.kodnest.musicmuse.entity.Song;

public interface SongService {

	public void saveSong(Song song);

	public boolean songExists(String name);

	public List<Song> fetchAllSongs();

	public void updateSong(Song s);



	

}
