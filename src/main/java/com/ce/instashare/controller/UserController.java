package com.ce.instashare.controller;

import java.util.Arrays;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


import com.ce.instashare.model.User;
import com.ce.instashare.services.UserService;
import com.ce.instashare.dto.user.request.SignInUserRequestDTO;
import com.ce.instashare.dto.user.request.SignUpUserRequestDTO;
import com.ce.instashare.dto.user.request.UserRequestDTO;
import com.ce.instashare.dto.user.response.SignInUserResponseDTO;
import com.ce.instashare.dto.user.response.UserResponseDTO;

@RestController
@RequestMapping("/user")
public class UserController {

	private static Logger logger = LogManager.getLogger(UserController.class);	

	@Autowired
	UserService userService;	

	@RequestMapping(value = "/signup",method = RequestMethod.POST)
	public ResponseEntity<?> signup(@RequestBody SignUpUserRequestDTO userDto) {		
		try {
			UserResponseDTO response = userService.signup(userDto);
			return new ResponseEntity<UserResponseDTO>(response,HttpStatus.CREATED);
		}
		catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(),HttpStatus.BAD_REQUEST);
		}		 
	}
	@RequestMapping(value = "/signin", method = RequestMethod.POST)
	public ResponseEntity<?> signin(@RequestBody SignInUserRequestDTO login){		
		try {
			SignInUserResponseDTO response = userService.signin(login);
			return new ResponseEntity<SignInUserResponseDTO>(response,HttpStatus.OK);
		}
		catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(),HttpStatus.BAD_REQUEST);
		}		
	}	
	@RequestMapping(method = RequestMethod.PUT)
	public ResponseEntity<?> updateUser(@RequestBody UserRequestDTO userDto) {		
		try {
			UserResponseDTO response = userService.updateUser(userDto);
			return new ResponseEntity<UserResponseDTO>(response,HttpStatus.OK);
		}
		catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(),HttpStatus.BAD_REQUEST);
		}
		 
	}
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<User> getUser(@PathVariable("id") String id) {
		User user = userService.getById(id);
		if (user == null) {
			logger.debug("User with id " + id + " does not exists");
			return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
		}
		logger.debug("Found User:: " + user);
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}


	@RequestMapping(value = "/getallusers",method = RequestMethod.GET)
	public ResponseEntity<List<User>> getAllUsers() {
		List<User> users = userService.getAll();
		if (users.isEmpty()) {
			logger.debug("Users does not exists");
			return new ResponseEntity<List<User>>(HttpStatus.NO_CONTENT);
		}
		logger.debug("Found " + users.size() + " Users");
		logger.debug(users);
		logger.debug(Arrays.toString(users.toArray()));
		return new ResponseEntity<List<User>>(users, HttpStatus.OK);
	}
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> deleteUser(@PathVariable("id") String id) {
		User user = userService.getById(id);
		if (user == null) {
			logger.debug("User with id " + id + " does not exists");
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		} else {
			userService.delete(user);
			logger.debug("User with id " + id + " deleted");
			return new ResponseEntity<Void>(HttpStatus.GONE);
		}
	}
	
}
