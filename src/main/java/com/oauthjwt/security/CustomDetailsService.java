package com.oauthjwt.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.oauthjwt.dao.UserDAO;
import com.oauthjwt.model.UserModel;

@Service
public class CustomDetailsService implements UserDetailsService {

	@Autowired
	UserDAO userDAO;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserModel userModel = userDAO.getUserDetails(username);
		CustomUser customUser = new CustomUser(userModel);
		return customUser;
	}

}