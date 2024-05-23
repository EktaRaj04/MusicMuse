package com.kodnest.musicmuse.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import com.kodnest.musicmuse.entity.Playlist;
import com.kodnest.musicmuse.entity.Song;
@Repository
public interface PlaylistRepository extends JpaRepository<Playlist, Integer> {

	Playlist findByName(String name);


}
