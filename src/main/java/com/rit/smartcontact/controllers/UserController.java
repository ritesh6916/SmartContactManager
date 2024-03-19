package com.rit.smartcontact.controllers;

import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.rit.smartcontact.helpers.Message;
import com.rit.smartcontact.persistence.UserRepository;
import com.rit.smartcontact.templates.User;

import jakarta.servlet.http.HttpSession;

@Controller
public class UserController {

	Logger logger = LoggerFactory.getLogger(getClass().getName());

	@Autowired
	UserRepository userRepository;

	/*
	 * The model attribute matches the fields form view and user-template and assign
	 * value form view to template class only if fields matches
	 */
	// request will come from sign-up page
	@PostMapping("/do-register")
	public String registerNewUser(@ModelAttribute("user") User user,
			@RequestParam(value = "agree", defaultValue = "false") boolean agree, Model m, HttpSession session) {

		try {

			if (!agree) {

				throw new Exception("Please accept the terms & conditions...");
			}
			user.setRole("ROLE_USER");
			user.setEnabled(true);

			logger.info(user.toString());

			userRepository.save(user);
			session.setAttribute("message", new Message("Successfully Registered !!", "alert-success"));
			m.addAttribute("user", new User()); // return new object back
			return "signup";

		} catch (SQLException e) {
			e.printStackTrace();
			session.setAttribute("message", new Message("Something went wrong with DB", "alert-danger"));
			m.addAttribute("user", user); // return same object back
			return "signup";

		} catch (Exception e) {
			e.printStackTrace();
			m.addAttribute("user", user); // return same object back
			session.setAttribute("message", new Message(e.getMessage(), "alert-danger"));
			return "signup";
		}

	}

}
