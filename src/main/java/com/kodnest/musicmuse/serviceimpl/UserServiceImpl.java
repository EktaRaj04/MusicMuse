package com.kodnest.musicmuse.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.kodnest.musicmuse.entity.User;
import com.kodnest.musicmuse.repository.UserRepository;
import com.kodnest.musicmuse.service.UserService;
@Service
public class UserServiceImpl implements UserService {
	@Autowired
	UserRepository userrepository;

	@Override
	public void saveUser(User user) {
		userrepository.save(user);
	
	}

	@Override
	public boolean emailExists(User user) {
		User existinguser=userrepository.findByEmail(user.getEmail());
		if(existinguser!=null) {
			System.out.println("present");
			return true;
		}
		else {
			System.out.println("absent");
		 return false;
		}
		
	}

	@Override
	public boolean validUser(String email, String password) {
		User user = userrepository.findByEmail(email);
		String dbpwd = user.getPassword();
		if(password.equals(dbpwd)) {
			return true;
		}else {
		return false;
		}
	    }
	@Override
	public String getRole(String email) {
		User dbuser = userrepository.findByEmail(email);
		return dbuser.getRole();
	}

	@Override
	public User getUser(String mail) {
		return userrepository.findByEmail(mail);
	}

	@Override
	public void updateUser(User user) {	
		userrepository.save(user);
	}
}