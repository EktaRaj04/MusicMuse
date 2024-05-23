package com.kodnest.musicmuse.service;

import org.springframework.beans.factory.annotation.Autowired;


import com.kodnest.musicmuse.entity.User;
import com.kodnest.musicmuse.repository.UserRepository;

public interface UserService {

	void saveUser(User user);

	boolean emailExists(User user);

	boolean validUser(String email, String password);

	String getRole(String email);

	User getUser(String mail);

	void updateUser(User user);



}
