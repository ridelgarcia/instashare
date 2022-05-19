package com.ce.instashare.dto.storage.request;

import java.util.Arrays;

public class UploadFileRequestDTO {

	private String data;
	
	private String name;
	
	private String path;
	
	private String userId;

	public UploadFileRequestDTO() {
		
	}

	public UploadFileRequestDTO(String data, String name, String path, String userId) {
		
		this.data = data;
		this.name = name;
		this.path = path;
		this.userId = userId;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	@Override
	public String toString() {
		return "UploadFileRequestDTO [data=" + data + ", name=" + name + ", path=" + path + ", userId="
				+ userId + "]";
	}

		
	
}
