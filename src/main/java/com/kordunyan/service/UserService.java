package com.kordunyan.service;

import com.kordunyan.dao.UserDAO;
import com.kordunyan.domain.Role;
import com.kordunyan.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

	@Autowired
	private UserDAO userDAO;

	public void createUser(User user) {
		createUserWithRole(user, new Role(Role.USER));
	}

	public void createAdmin(User user) {
		createUserWithRole(user, new Role(Role.ADMIN));
	}

	public User findOne(String email) {
		return userDAO.findOne(email);
	}


	public void createUserWithRole(User user, Role role) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		user.setPassword(encoder.encode(user.getPassword()));
		List<Role> roleList = new ArrayList<>();
		roleList.add(role);
		user.setRoles(roleList);
		userDAO.save(user);
	}

}
