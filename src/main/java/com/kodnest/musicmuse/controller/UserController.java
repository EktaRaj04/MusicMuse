package com.kodnest.musicmuse.controller;

import java.util.List;




import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.kodnest.musicmuse.entity.Song;
import com.kodnest.musicmuse.entity.User;
import com.kodnest.musicmuse.service.SongService;
import com.kodnest.musicmuse.service.UserService;
import com.kodnest.musicmuse.serviceimpl.UserServiceImpl;

import jakarta.servlet.http.HttpSession;

@Controller
public class UserController {
	@Autowired
	UserService userservice;
	
	@Autowired
	SongService songservice;
	
	@PostMapping("/register")
	public String userData(@ModelAttribute User user) {
		
		//to check the user with the email is present or not
		boolean userExists=userservice.emailExists(user);
		
		if(userExists==false) {
			userservice.saveUser(user);
			System.out.println("User added successfully");
			
		}
		else {
			System.out.println("Duplicate user");
		}
		return "login";
}
	@PostMapping("/validate")
	public String validate(@RequestParam("email") String email, @RequestParam("password") String password, HttpSession session, Model model) {
		if(userservice.validUser(email, password)==true) {
			session.setAttribute("email", email);
			String role=userservice.getRole(email);
			
			if(role.equals("admin")) {
			return "adminhome";
		}
		else {
			User user = userservice.getUser(email);
			boolean userstatus = user.isPremium();
			List <Song> fetchAllSongs=  songservice.fetchAllSongs();
			model.addAttribute("songs",fetchAllSongs);
			model.addAttribute("isPremium", userstatus);
			
			return "customerhome";
		}
		}
			else {
				return "login";
			}
		
	}
	
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "login";
		
	}
}
