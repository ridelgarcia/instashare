package com.ce.instashare.dto.storage.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonProperty;

public class NavigateRequestDTO {
	
	@JsonProperty(required = true)
	@NotEmpty
	@NotBlank
	private String userId;
	
	@JsonProperty(required = true)
	@NotEmpty
	@NotBlank
	private String path;

	public NavigateRequestDTO() {		
	}

	public NavigateRequestDTO(@NotEmpty @NotBlank String userId, @NotEmpty @NotBlank String path) {
		this.userId = userId;
		this.path = path;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	@Override
	public String toString() {
		return "NavigateRequestDTO [userId=" + userId + ", path=" + path + "]";
	}
}
