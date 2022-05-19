package com.ce.instashare.dto.storage.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CreateFolderRequestDTO {
	
	@JsonProperty(required = true)
	@NotEmpty
	@NotBlank
	private String userId;
	
	@JsonProperty(required = true)
	@NotEmpty
	@NotBlank
	private String path;
	
	@JsonProperty(required = true)
	@NotEmpty
	@NotBlank
	private String folderName;

	public CreateFolderRequestDTO() {
		
	}

	public CreateFolderRequestDTO(@NotEmpty @NotBlank String userId, @NotEmpty @NotBlank String path,
			@NotEmpty @NotBlank String folderName) {		
		this.userId = userId;
		this.path = path;
		this.folderName = folderName;
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

	public String getFolderName() {
		return folderName;
	}

	public void setFolderName(String folderName) {
		this.folderName = folderName;
	}

	@Override
	public String toString() {
		return "CreateFolderRequestDTO [userId=" + userId + ", path=" + path + ", folderName=" + folderName + "]";
	}
}
