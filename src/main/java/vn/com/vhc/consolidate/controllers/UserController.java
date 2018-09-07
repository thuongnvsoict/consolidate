package vn.com.vhc.consolidate.controllers;

import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import vn.com.vhc.consolidate.models.User;
import vn.com.vhc.consolidate.services.UserService;


@RestController
public class UserController {
	UserService us = new UserService();
	@RequestMapping(value = "/user", method = RequestMethod.GET)
	public User checkUserPassword(
			@RequestParam(value = "username", required = true) String username) throws NoSuchAlgorithmException, SQLException {
		return us.getUserInfo(username);
	}
}
