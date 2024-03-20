package com.rit.smartcontact.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.rit.smartcontact.persistence.UserRepository;
import com.rit.smartcontact.templates.User;

public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		// To get user details form database by Email(username)
		User user = userRepository.getUserByUserName(username);

		if (user == null) {

			throw new UsernameNotFoundException(username);
		}

		CustomUserDetails customeCustomUserDetails = new CustomUserDetails(user);

		return customeCustomUserDetails;
	}

}
