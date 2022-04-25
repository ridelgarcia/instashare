package com.ce.instashare.dto.user.response;

import com.ce.instashare.dto.role.response.RoleResponseDTO;

public class UserResponseDTO {
	
	private String id;
	
	private String name;

	private String lastname;

	private String email;    

    private RoleResponseDTO role;
    
    public UserResponseDTO() {
    	this.id = "";
		this.name = "";
		this.lastname = "";
		this.email = "";
		this.role = null;
	}

	

	public UserResponseDTO(String id, String name, String lastname, String email, RoleResponseDTO role) {
		this.id = id;
		this.name = name;
		this.lastname = lastname;
		this.email = email;
		this.role = role;
	}



	public String getId() {
		return id;
	}



	public void setId(String id) {
		this.id = id;
	}



	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public RoleResponseDTO getRole() {
		return role;
	}

	public void setRole(RoleResponseDTO role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return "UserResponseDTO [name=" + name + ", lastname=" + lastname + ", email=" + email + ", role=" + role.toString() + "]";
	}
    
}
