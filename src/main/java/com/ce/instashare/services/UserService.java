package com.ce.instashare.services;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ce.instashare.model.User;
import com.ce.instashare.repositories.UserRepository;

import java.util.ArrayList;
import java.util.List;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;
@Service
public class UserService implements UserDetailsService {

	@Autowired
	private UserRepository rep;
	
	@Autowired
	PasswordEncoder encoder;
	
	public User save(User entity) {
		return rep.save(entity);
	}

	public void register(User user){
		user.setPassword(encoder.encode(user.getPassword()));
		save(user);
	}
	
	public User getById(Serializable id) {
		return rep.findById((Long)id).get();
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
			return new org.springframework.security.core.userdetails.User(users.get(0).getEmail(),users.get(0).getPassword(),
					new ArrayList<>());
		} else {
			throw new UsernameNotFoundException("User not found with email: " + username);
		}
	}

}