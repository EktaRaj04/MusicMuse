package com.kodnest.musicmuse.serviceimpl;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kodnest.musicmuse.entity.Song;
import com.kodnest.musicmuse.entity.User;
import com.kodnest.musicmuse.repository.SongRepository;
import com.kodnest.musicmuse.service.SongService;

@Service
public class SongServiceImpl implements SongService {
	@Autowired
	SongRepository songrepository;

	@Override
	public void saveSong(Song song) {
		songrepository.save(song);
		
	}

	@Override
	public boolean songExists(String name) {
		Song song=songrepository.findByName(name);
		if(song!=null) {
			return true;
		}else {
		return false;
	}
	}

	@Override
	public List<Song> fetchAllSongs() {
		List<Song> songs = songrepository.findAll();
		return songs;
	}

	@Override
	public void updateSong(Song song) {
		songrepository.save(song)	;	
	}
}