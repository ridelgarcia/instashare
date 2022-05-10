package com.ce.instashare.dto.user.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CheckEmailRequestDTO {
	@JsonProperty(required = true)
	@NotEmpty
	@NotBlank
	private String email;

	public CheckEmailRequestDTO() {
		this.email = "";
	}

	public CheckEmailRequestDTO(@NotEmpty @NotBlank String email) {
		this.email = email;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "CheckEmailRequestDTO [email=" + email + "]";
	}
}
