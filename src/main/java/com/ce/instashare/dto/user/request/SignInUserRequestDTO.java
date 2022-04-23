package com.ce.instashare.dto.user.request;
import javax.validation.constraints.NotNull;

public class SignInUserRequestDTO {
	
	@NotNull
	private String email;
	@NotNull
    private String password;
	
	public SignInUserRequestDTO() {
		this.email = "";
		this.password = "";
	}
	
	public SignInUserRequestDTO(String email, String password) {
		this.email = email;
		this.password = password;
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

	@Override
	public String toString() {
		return "SignInUserRequestDTO [email=" + email + ", password=" + password + "]";
	}	
}
