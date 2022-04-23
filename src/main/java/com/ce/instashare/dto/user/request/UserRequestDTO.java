package com.ce.instashare.dto.user.request;

import com.ce.instashare.dto.role.request.RoleRequestDTO;

public class UserRequestDTO {
	
	private String id;
	
	private String name;

	private String lastname;

	private String email;    

    private RoleRequestDTO role;
    
    public UserRequestDTO() {
		this.id = "";
		this.name = "";
		this.lastname = "";
		this.email = "";
		this.role = null;
	}

	public UserRequestDTO(String id, String name, String lastname, String email, RoleRequestDTO role) {
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

	public RoleRequestDTO getRole() {
		return role;
	}

	public void setRole(RoleRequestDTO role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return "UserRequestDTO [id=" + id + ", name=" + name + ", lastname=" + lastname + ", email=" + email + ", role="
				+ role.toString() + "]";
	}   

}
