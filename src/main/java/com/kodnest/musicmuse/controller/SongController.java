 package com.kodnest.musicmuse.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.kodnest.musicmuse.entity.Song;
import com.kodnest.musicmuse.service.SongService;

@Controller
public class SongController {
	@Autowired
	SongService songservice;
	
	@PostMapping("/addsongs")
	public String saveSong(@ModelAttribute Song song) {
		boolean songExists= songservice.songExists(song.getName());
		if(songExists==false) {
			songservice.saveSong(song);
			System.out.println("Song added successfully");
			
		}
		else {
			System.out.println("Song already exists");
		}
		return "adminhome";
		
	}
	@GetMapping("/playsongs")
	public String playSongs(Model model) {
		boolean premium=true;
		if(premium) {
		List <Song> songslist=  songservice.fetchAllSongs();
		model.addAttribute("songs", songslist);
		return "viewsongs";
		}
		else {
			return "pay";
		}
	}
	
	@GetMapping("/viewsongs")
	public String viewSongs(Model model) {
		List <Song> songslist=  songservice.fetchAllSongs();
		model.addAttribute("songs", songslist);
		System.out.println(songslist);
		return "viewsongs";
	}
	}
