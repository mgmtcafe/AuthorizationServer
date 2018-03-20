package com.oauthjwt.dao;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.oauthjwt.model.Signup;
import com.oauthjwt.model.UserModel;

@Repository
@Transactional
public class UserDAO {

	@Autowired
	SessionFactory sessionFactory;

	public UserDAO(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Autowired
	JdbcTemplate jdbcTemplate;

	class Authorities {
		String auth;

		public String getAuth() {
			return auth;
		}

		public void setAuth(String auth) {
			this.auth = auth;
		}

	}

	public UserModel getUserDetails(String username) {

		Collection<GrantedAuthority> grantedAuthoritiesList = new ArrayList<GrantedAuthority>();
		Authorities authorities = new Authorities();
		String sqlQuery = "SELECT * FROM USERS WHERE USERNAME=?";
		List<UserModel> list = jdbcTemplate.query(sqlQuery, new String[] { username }, (ResultSet rs, int rowNum) -> {
			UserModel userModel = new UserModel();
			userModel.setUsername(username);
			userModel.setPassword(rs.getString("PASSWORD"));
			authorities.setAuth(rs.getString("AUTHORITIES"));
			return userModel;
		});

		if (list != null && list.size() > 0) {
			String auth[] = authorities.getAuth().split(",");
			for (String s : auth) {
				grantedAuthoritiesList.add(new SimpleGrantedAuthority(s));
			}
			list.get(0).setGrantedAuthoritiesList(grantedAuthoritiesList);
			return list.get(0);
		}

		return null;
	}

	public String createUser(Signup userModel) {
		Session session = this.sessionFactory.openSession();
		Transaction tx = session.beginTransaction();

		try {
			tx = session.beginTransaction();
			session.save(userModel);
			tx.commit();

		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
		return "success";
	}
}