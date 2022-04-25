package com.ce.instashare.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import com.ce.instashare.services.UserService;
import com.ce.instashare.dto.user.request.SignInUserRequestDTO;
import com.ce.instashare.dto.user.request.SignUpUserRequestDTO;
import com.ce.instashare.dto.user.request.UserRequestDTO;
import com.ce.instashare.dto.user.response.SignInUserResponseDTO;
import com.ce.instashare.dto.user.response.UserPageResponseDTO;
import com.ce.instashare.dto.user.response.UserResponseDTO;

@RestController
@RequestMapping("/user")
public class UserController {

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
	@PreAuthorize("hasRole('ADMIN')")
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
	public ResponseEntity<?> getUser(@PathVariable("id") String id) {
		try {
			UserResponseDTO response = userService.getUser(id);
			return new ResponseEntity<UserResponseDTO>(response,HttpStatus.OK);
		}
		catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(),HttpStatus.BAD_REQUEST);
		}
	}
	@PreAuthorize("hasAuthority('ADMIN')")
	@RequestMapping(value = "/getallusers",method = RequestMethod.GET)
	public ResponseEntity<?> getAllUsers(
			@RequestParam(required = false, name = "page",
			defaultValue = "0") int page,
	@RequestParam(required = false, name = "size",
			defaultValue = "20") int size,
	@RequestParam(required = false, name = "sortField",
			defaultValue = "createdAt") String sortField,
	@RequestParam(required = false, name = "direction",
			defaultValue = "DESC") String direction,
	@RequestParam(required = false, name = "status") List<String> status,
	@RequestParam(required = false, name = "search") String search) {
		try {
			UserPageResponseDTO response = userService.getAllUsers(page, size, sortField, direction, status, search);
			return new ResponseEntity<UserPageResponseDTO>(response,HttpStatus.OK);
		}
		catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(),HttpStatus.BAD_REQUEST);
		}
		
	}
	@PreAuthorize("hasAuthority('ADMIN')")
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> deleteUser(@PathVariable("id") String id) {
		try	{
			userService.deleteUser(id);
			return new ResponseEntity<Void>(HttpStatus.OK);
		}
		catch (Exception e) {
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}
	}	
}
