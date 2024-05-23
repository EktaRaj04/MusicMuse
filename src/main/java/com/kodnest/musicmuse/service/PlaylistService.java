package com.kodnest.musicmuse.service;

import java.util.List;


import com.kodnest.musicmuse.entity.Playlist;
import com.kodnest.musicmuse.entity.Song;

public interface PlaylistService {


	public void addPlaylist(Playlist playlist);

	public List<Playlist> fetchAllPlaylists();



}
