package com.ce.instashare.dto.user.request;

import javax.validation.constraints.NotNull;
public class SignUpUserRequestDTO {
	
	@NotNull
    private String name;
	@NotNull
	private String lastname;
	@NotNull
	private String email;
	@NotNull
    private String password;
	@NotNull
    private Integer role;
	
	public SignUpUserRequestDTO() {
		this.name = "";
		this.lastname = "";
		this.email = "";
		this.password = "";
		this.role = 0;
	}
	
	public SignUpUserRequestDTO(String name, String lastname, String email, String password, Integer role) {
		this.name = name;
		this.lastname = lastname;
		this.email = email;
		this.password = password;
		this.role = role;
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getRole() {
		return role;
	}

	public void setRole(Integer role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return "SignUpRequestDTO [name=" + name + ", lastname=" + lastname + ", email=" + email + ", password="
				+ password + ", role=" + role + "]";
	}

}
