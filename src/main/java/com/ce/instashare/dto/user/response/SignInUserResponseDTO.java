package com.ce.instashare.dto.user.response;

import com.ce.instashare.dto.role.response.RoleResponseDTO;

public class SignInUserResponseDTO {

	private String name;

	private String lastname;

	private String email;  

    private RoleResponseDTO role;

    private String token;
    
    public SignInUserResponseDTO(){
		this.name = "";
		this.lastname = "";
		this.email = "";
		this.role = null;
		this.token = "";
	}

	public SignInUserResponseDTO(String name, String lastname, String email, RoleResponseDTO role, String token) {
		this.name = name;
		this.lastname = lastname;
		this.email = email;
		this.role = role;
		this.token = token;
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

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	@Override
	public String toString() {
		return "SignInUserResponseDTO [name=" + name + ", lastname=" + lastname + ", email=" + email + ", role=" + role.toString()
				+ ", token=" + token + "]";
	}
    
    
}
