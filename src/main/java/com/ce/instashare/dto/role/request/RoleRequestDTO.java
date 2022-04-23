package com.ce.instashare.dto.role.request;



public class RoleRequestDTO {
	
	
	private String id;	
	
	private String rolename;	
	
	private String rolecode;
	
	public RoleRequestDTO() {
		this.id = "";
		this.rolename = "";
		this.rolecode = "";
	}

	public RoleRequestDTO(String id, String rolename, String rolecode) {
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

	public String getRolecode() {
		return rolecode;
	}

	public void setRolecode(String rolecode) {
		this.rolecode = rolecode;
	}

	@Override
	public String toString() {
		return "RoleRequestDTO [id=" + id + ", rolename=" + rolename + ", rolecode=" + rolecode + "]";
	}	
	
}
