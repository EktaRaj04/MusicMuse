package com.kodnest.musicmuse.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import com.kodnest.musicmuse.entity.User;
@Repository
public interface UserRepository extends JpaRepository<User, Integer>{

	public User findByEmail(String email);


}
