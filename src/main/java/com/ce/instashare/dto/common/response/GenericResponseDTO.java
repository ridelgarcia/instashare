package com.ce.instashare.dto.common.response;

public class GenericResponseDTO {

	private int errorCode;
	
	private String errorDescription;
	
	public GenericResponseDTO() {
		this.errorCode = 0;
		this.errorDescription = "";
	}

	public GenericResponseDTO(int errorCode, String errorDescription) {
		this.errorCode = errorCode;
		this.errorDescription = errorDescription;
	}

	public int getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorDescription() {
		return errorDescription;
	}

	public void setErrorDescription(String errorDescription) {
		this.errorDescription = errorDescription;
	}

	@Override
	public String toString() {
		return "GenericResponseDTO [errorCode=" + errorCode + ", errorDescription=" + errorDescription + "]";
	}	
}
