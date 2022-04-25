package com.ce.instashare.services;


import java.util.List;
import java.util.Set;

import org.springframework.context.ApplicationContext;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
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
import com.ce.instashare.dto.user.response.UserPageResponseDTO;
import com.ce.instashare.dto.user.response.UserResponseDTO;
import com.ce.instashare.model.Role;
import com.ce.instashare.model.User;
import com.ce.instashare.repositories.UserRepository;
import com.ce.instashare.security.Authority;

import java.util.ArrayList;
import java.util.HashSet;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
			String error = "Not Registered, email already exist:: " + userDto.getEmail();
			logger.debug(error);
			throw new Exception(error);
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
				logger.debug(e.getMessage());
				throw e;
			}
		}
		else{
			String error = "Invalid login attempt for user :: " + login.getEmail();
			logger.debug(error);
			throw new Exception(error);
		}
		return response;
	}
	
	public UserResponseDTO updateUser(UserRequestDTO user) throws  Exception{
		User existingUser = this.getById(user.getId());
		if (existingUser == null) {
			String error = "User with id " + user.getId() + " does not exists";
			logger.debug(error);
			throw new Exception(error);
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
	public UserResponseDTO getUser(String id)throws Exception{
		User user = getById(id);
		UserResponseDTO userResponse = null;
		if(user == null) {
			String error = "User with id " + id + " does not exists";
			logger.debug(error);
			throw new Exception(error);
		}
			
		else {
			userResponse = modelMapper.map(user, UserResponseDTO.class);
		}
		return userResponse;
	}
	public UserPageResponseDTO getAllUsers(int page,int size,String sortField,String direction,List<String> status,String search) throws Exception{
		Sort sort = Sort.by(sortField);
		if(direction == "ASC")
			sort = sort.ascending();
		else if(direction == "DESC")
			sort = sort.descending();
		PageRequest p = PageRequest.of(page, size, sort);
		Page<User> pageR = rep.findAll(p);		
		UserPageResponseDTO response = new UserPageResponseDTO();
		response.setStart(pageR.getNumber());
		response.setEnd(pageR.getTotalElements());
		response.setSize(pageR.getSize());
		List<User> users = pageR.toList();
		List<UserResponseDTO> usersR = new ArrayList<UserResponseDTO>();
		for (User user : users) {
			usersR.add(modelMapper.map(user, UserResponseDTO.class));
		}
		response.setUsers(usersR);
		return response;
	}
	
	public void deleteUser(String id)throws Exception{
		User user = getById(id);		
		if (user == null) {
			String error = "User with id " + id + " does not exists";
			logger.debug(error);
			throw new Exception(error);
		} else {
			delete(user);
			logger.debug("User with id " + id + " deleted");			
		}		
	}	
	public User getByEmailAndPassword(String email,String password){
		User user = null;
		List<User> users = rep.getByEmail(email);
		if(users.size() > 0){			
			PasswordEncoder encoder = context.getBean("BCryptPasswordEncoder",PasswordEncoder.class); 
			if(encoder.matches(password,users.get(0).getPassword())){				
				user = users.get(0);
			}			
		}
		return user;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		List<User> users = rep.getByEmail(username); 

		if (users.size() > 0) {
			
			Set<Authority> authorities = new HashSet<Authority>();
			authorities.add(new Authority(users.get(0).getRole().getRoleName()));
			return new org.springframework.security.core.userdetails.User(users.get(0).getEmail(),users.get(0).getPassword(),
					authorities);
		} else {
			throw new UsernameNotFoundException("User not found with email: " + username);
		}
	}
	private void authenticate(String username, String password) throws Exception {
		try {			
			Authentication authentication = context.getBean(AuthenticationManager.class).authenticate(new UsernamePasswordAuthenticationToken(username, password));
			SecurityContextHolder.getContext().setAuthentication(authentication);
		} catch (DisabledException e) {
			throw new Exception("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			throw new Exception("INVALID_CREDENTIALS", e);
		}
	}
	
	private User save(User entity) {
		return rep.save(entity);
	}

	private void register(User user){
		
		user.setPassword(context.getBean("BCryptPasswordEncoder",PasswordEncoder.class).encode(user.getPassword()));
		save(user);
	}
	
	private User getById(String id) {
		return rep.findById(id).get();
	}

	
	private void delete(User user) {
		rep.delete(user);
	}

	public List<User> getByEmail(String email){
		return rep.getByEmail(email);
	}


}