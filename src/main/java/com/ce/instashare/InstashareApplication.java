package com.ce.instashare;

import java.io.Console;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.ce.instashare.dto.role.request.RoleRequestDTO;
import com.ce.instashare.dto.user.request.SignUpUserRequestDTO;
import com.ce.instashare.model.Role;
import com.ce.instashare.repositories.RoleRepository;
import com.ce.instashare.repositories.UserRepository;
import com.ce.instashare.services.UserService;




@SpringBootApplication
public class InstashareApplication {
	
	@Autowired
	private UserService userService;

	public static void main(String[] args) {
		SpringApplication.run(InstashareApplication.class, args);
	}
	@Bean
    ApplicationRunner init(RoleRepository repository) {
        return (ApplicationArguments args) ->  dataRoleSetup(repository);
    } 
	
    public void dataRoleSetup(RoleRepository repository){
    	Role admin = new Role();
    	Role regularUser = new Role();
    	admin.setRoleName("Administrator");
    	admin.setRoleCode(0);
    	regularUser.setRoleName("Regular User");
    	regularUser.setRoleCode(1);
    	repository.save(admin);
    	repository.save(regularUser);
    	RoleRequestDTO roleAdminRequest = new RoleRequestDTO(admin.getId(),admin.getRoleName(),admin.getRoleCode());
    	SignUpUserRequestDTO dto = new SignUpUserRequestDTO("Ridel", "Garcia", "rmora900121@gmail.com", "90012137784", roleAdminRequest);
    	try {
    		userService.signup(dto);
    	}
    	catch (Exception e) {
			
		}
    }    
}
