package com.rit.smartcontact.controllers;

import java.sql.SQLException;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.rit.smartcontact.helpers.Message;
import com.rit.smartcontact.persistence.UserRepository;
import com.rit.smartcontact.templates.User;

@Controller
public class HomeController {

	Logger logger = LoggerFactory.getLogger(getClass().getName());

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Autowired
	UserRepository userRepository;

	@GetMapping("/")
	public String showHome(Model model) {
		model.addAttribute("title", "Home - Smart Contact Manager");
		return "home";
	}

	@GetMapping("/about")
	public String showAbout(Model model) {
		model.addAttribute("title", "About - Smart Contact Manager");
		return "about";
	}

	@GetMapping("/signup")
	public String showSignup(Model model) {
		model.addAttribute("title", "Register - Smart Contact Manager");
		model.addAttribute("user", new User()); // To send and receive data
		return "signup";
	}

	@GetMapping("/signin")
	public String signup(Model m) {
		m.addAttribute("title", "Signin - Smart Contact Manager");
		return "login";
	}

	/*
	 * The model attribute matches the fields form view and user-template and assign
	 * value form view to template class only if fields matches
	 */
	// request will come from sign-up page
	@PostMapping("/do-register")
	public String registerNewUser(@Valid @ModelAttribute("user") User user, BindingResult result,
			@RequestParam(value = "agree", defaultValue = "false") boolean agree, Model m, HttpSession session) {

		try {

			if (!agree) {

				throw new Exception("Please accept the terms & conditions...");
			}

			if (result.hasErrors()) {

				System.out.println(result.toString());
				m.addAttribute("user", user);

				return "signup";
			}

			user.setRole("ROLE_USER");
			user.setEnabled(true);
			user.setPassword(passwordEncoder.encode(user.getPassword()));
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
