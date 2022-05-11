package com.ce.instashare.dto.storage.request;

import org.springframework.web.multipart.MultipartFile;

public class UploadFileRequestDTO {

	private MultipartFile fileContent;
	
	private String path;
	
	private String userId;

	public UploadFileRequestDTO() {
		
	}

	public UploadFileRequestDTO(MultipartFile fileContent, String path, String userId) {
		
		this.fileContent = fileContent;
		this.path = path;
		this.userId = userId;
	}

	public MultipartFile getFileContent() {
		return fileContent;
	}

	public void setFileContent(MultipartFile fileContent) {
		this.fileContent = fileContent;
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
		return "UploadFileRequestDTO [fileContent=" + fileContent + ", path=" + path + ", userId=" + userId + "]";
	}	
	
}
