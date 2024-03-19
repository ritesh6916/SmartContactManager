package com.rit.smartcontact.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.rit.smartcontact.templates.User;

@Controller
public class UserController {

	Logger logger = LoggerFactory.getLogger(getClass().getName());

	/*
	 * The model attribute matches the fields form view and user-template and assign
	 * value form view to template class only if fields matches
	 */
	// request will come from sign-up page
	@PostMapping("/do-register")
	public String registerNewUser(@ModelAttribute("user") User user,
			@RequestParam(value = "agree", defaultValue = "false") boolean agree, Model m) {

		user.setRole("ROLE_USER");
		user.setEnabled(true);

		if (!agree) {
			System.out.println("User doesn't aggreed terms & conditions...");
		}

		logger.info(user.toString());

		m.addAttribute("user", user); // return same object back
		return "signup";
	}

}
