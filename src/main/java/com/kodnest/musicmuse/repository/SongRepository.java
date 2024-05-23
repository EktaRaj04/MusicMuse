package com.kodnest.musicmuse.repository;

import org.springframework.data.jpa.repository.JpaRepository;


import com.kodnest.musicmuse.entity.Song;

public interface SongRepository extends JpaRepository<Song, Integer>{

	public Song findByName(String name);

}
