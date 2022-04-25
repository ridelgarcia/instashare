package com.ce.instashare.dto.role.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RoleRequestDTO {
	
	@JsonProperty(required = true)
	@NotEmpty
	@NotBlank
	private String id;
	
	@JsonProperty(required = true)
	@NotEmpty
	@NotBlank
	private String rolename;	
	
	@JsonProperty(required = true)
	@NotEmpty
	@NotBlank
	private int rolecode;
	
	public RoleRequestDTO() {
		this.id = "";
		this.rolename = "";
		this.rolecode = 0;
	}

	public RoleRequestDTO(String id, String rolename, int rolecode) {
		this.id = id;
		this.rolename = rolename;
		this.rolecode = rolecode;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getRolename() {
		return rolename;
	}

	public void setRolename(String rolename) {
		this.rolename = rolename;
	}

	public int getRolecode() {
		return rolecode;
	}

	public void setRolecode(int rolecode) {
		this.rolecode = rolecode;
	}

	@Override
	public String toString() {
		return "RoleRequestDTO [id=" + id + ", rolename=" + rolename + ", rolecode=" + rolecode + "]";
	}	
	
}
