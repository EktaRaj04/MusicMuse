package com.kodnest.musicmuse.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.kodnest.musicmuse.entity.Playlist;
import com.kodnest.musicmuse.entity.Song;
import com.kodnest.musicmuse.service.PlaylistService;
import com.kodnest.musicmuse.service.SongService;

@Controller
public class PlaylistController {
	@Autowired
	PlaylistService playlistservice;
	@Autowired
	SongService songservice;
	
	@GetMapping("/createplaylist")
	public String createPlaylist(Model model) {
		List <Song> songslist=  songservice.fetchAllSongs();
		model.addAttribute("songs", songslist);
		return "createplaylist";
	}
	@PostMapping("/addplaylist")
	public String addPlaylist(@ModelAttribute Playlist playlist) {
		playlistservice.addPlaylist(playlist);
		
		//updating the song_playlists table
		List<Song> songlist= playlist.getSongs();
		
		for(Song s:songlist) {
			s.getPlaylists().add(playlist);
			songservice.updateSong(s);
		}
		return "adminhome";
	}
	@GetMapping("/viewplaylist")
	public String viewPlaylists(Model model) {
	    List<Playlist> playlists = playlistservice.fetchAllPlaylists();
	    model.addAttribute("playlists", playlists);
	    return "viewplaylist";
	}
		
	
}
		
