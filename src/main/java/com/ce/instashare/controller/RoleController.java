package com.ce.instashare.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ce.instashare.dto.role.response.RoleResponseDTO;
import com.ce.instashare.services.RoleService;

@CrossOrigin
@RestController
@RequestMapping("/role")
public class RoleController {
	
	@Autowired
	RoleService roleService;
	
	@RequestMapping(value = "/getdefaultrole",method = RequestMethod.GET)
	public ResponseEntity<?> signup() {		
		try {
			RoleResponseDTO response = roleService.getUserDefaultRole();
			return new ResponseEntity<RoleResponseDTO>(response,HttpStatus.OK);
		}
		catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(),HttpStatus.BAD_REQUEST);
		}		 
	}
}
