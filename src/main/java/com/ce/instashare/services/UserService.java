package com.ce.instashare.services;

import java.io.Serializable;
import java.util.List;


import org.springframework.context.ApplicationContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.ce.instashare.config.JwtTokenUtil;
import com.ce.instashare.dto.user.request.SignInUserRequestDTO;
import com.ce.instashare.dto.user.request.SignUpUserRequestDTO;
import com.ce.instashare.dto.user.request.UserRequestDTO;
import com.ce.instashare.dto.user.response.SignInUserResponseDTO;
import com.ce.instashare.dto.user.response.UserResponseDTO;
import com.ce.instashare.model.Role;
import com.ce.instashare.model.User;
import com.ce.instashare.repositories.UserRepository;

import java.util.ArrayList;


import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
@Service
public class UserService implements UserDetailsService {
	
	private static Logger logger = LogManager.getLogger(UserService.class);
	private static final ModelMapper modelMapper = new ModelMapper();
	
	@Autowired
	private ApplicationContext context;
	
	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private UserRepository rep;
	
	
	
	public UserResponseDTO signup(SignUpUserRequestDTO userDto) throws Exception{
		List<User> users = this.getByEmail(userDto.getEmail());
		UserResponseDTO response = null;
		if(users.size() == 0){
			User user = modelMapper.map(userDto,User.class);		
			this.register(user);
			logger.debug("Registered:: " + user);
			response = modelMapper.map(user,UserResponseDTO.class);			
		}
		else{
			logger.debug("Not Registered, email already exist:: " + userDto.getEmail());
			throw new Exception("Not Registered email already exist");
		}
		return response;
	}
	
	public SignInUserResponseDTO signin(SignInUserRequestDTO login) throws Exception{
		User user = this.getByEmailAndPassword(login.getEmail(),login.getPassword());
		SignInUserResponseDTO response = null;
		if(user != null ){			
			try
			{
				this.authenticate(login.getEmail(), login.getPassword());
				final UserDetails userDetails = this
						.loadUserByUsername(login.getEmail());

				final String token = jwtTokenUtil.generateToken(userDetails);
				response = modelMapper.map(user,SignInUserResponseDTO.class);
				response.setToken(token);
				logger.debug("Login successful for user:: " + login.getEmail());				
			}
			catch (Exception e) {
				logger.debug("Invalid login attempt for user :: " + login.getEmail());
				throw new Exception("Bad Credentials " + login.getEmail());
			}
		}
		else{
			logger.debug("Invalid login attempt for user :: " + login.getEmail());
			throw new Exception("Bad Credentials " + login.getEmail());
		}
		return response;
	}
	
	public UserResponseDTO updateUser(UserRequestDTO user) throws  Exception{
		User existingUser = this.getById(user.getId());
		if (existingUser == null) {
			logger.debug("User with id " + user.getId() + " does not exists");
			throw new Exception("User not found");
		} else {
			existingUser.setEmail(user.getEmail());
			existingUser.setLastName(user.getLastname());
			existingUser.setName(user.getName());
			existingUser.setRole(modelMapper.map(user.getRole(),Role.class));
			this.save(existingUser);
			UserResponseDTO response = modelMapper.map(existingUser, UserResponseDTO.class);
			return response;
		}
	}
	
	private void authenticate(String username, String password) throws Exception {
		try {			
			context.getBean(AuthenticationManager.class).authenticate(new UsernamePasswordAuthenticationToken(username, password));
			
		} catch (DisabledException e) {
			throw new Exception("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			throw new Exception("INVALID_CREDENTIALS", e);
		}
	}
	
	public User save(User entity) {
		return rep.save(entity);
	}

	public void register(User user){
		user.setPassword(context.getBean(PasswordEncoder.class).encode(user.getPassword()));
		save(user);
	}
	
	public User getById(String id) {
		return rep.getById(id);
	}


	public List<User> getAll() {
		return rep.findAll();
	}

	
	public void delete(User user) {
		rep.delete(user);
	}

	public List<User> getByEmail(String email){
		return rep.getByEmail(email);
	}

	public User getByEmailAndPassword(String email,String password){
		User user = null;
		List<User> users = rep.getByEmail(email);
		if(users.size() > 0){
			if(context.getBean(PasswordEncoder.class).matches(password,users.get(0).getPassword())){
				user = users.get(0);
			}			
		}
		return user;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		List<User> users = rep.getByEmail(username); 

		if (users.size() > 0) {
			return new org.springframework.security.core.userdetails.User(users.get(0).getEmail(),users.get(0).getPassword(),
					new ArrayList<>());
		} else {
			throw new UsernameNotFoundException("User not found with email: " + username);
		}
	}

}