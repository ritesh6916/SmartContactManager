package com.rit.smartcontact.controllers;

import java.security.Principal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.rit.smartcontact.persistence.UserRepository;
import com.rit.smartcontact.templates.User;

@Controller
@RequestMapping("/user")
public class UserController {

	Logger logger = LoggerFactory.getLogger(getClass().getName());

	@Autowired
	UserRepository userRepository;

	@GetMapping("/index")
	public String showDashboard(Model m, Principal principal) {

		String emial = principal.getName();
		System.out.println(emial);

		User u = userRepository.getUserByUserName(emial);
		System.out.println(u);
		m.addAttribute("user", u);
		return "user/user_dashboard";
	}

}
