package com.revature.services;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.revature.dto.Credential;
import com.revature.models.User;

public interface UserService {
	UserService currentImplementation = new UserServiceImpl();

	User findById(int id);

	boolean saveUser(User user);

	boolean login(Credential cred, HttpSession httpSession);

	User findByUsername(String username);

}
