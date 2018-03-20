package com.oauthjwt.security;

import org.springframework.security.core.userdetails.User;

import com.oauthjwt.model.UserModel;

public class CustomUser extends User{

	private static final long serialVersionUID = 1L;

	public CustomUser(UserModel user) {
		super(user.getUsername(), user.getPassword(), user.getGrantedAuthoritiesList());
		// TODO Auto-generated constructor stub
	}

}
