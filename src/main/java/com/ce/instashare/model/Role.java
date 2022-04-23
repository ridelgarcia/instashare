package com.ce.instashare.model;

import java.time.Instant;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
@Entity
@Table(name = "[role]")
public class Role extends BaseEntity{
	
	@Column(name = "rolename")
	private String rolename;
	
	@Column(name = "rolecode")
	private int rolecode;
	
	public Role() {
		super();
	}

	public Role(String id, Instant createdAt, Instant modifiedAt, int version,String rolename, int rolecode) {
		super(id,createdAt,modifiedAt,version);
		this.rolename = rolename;
		this.rolecode = rolecode;
	}

	public String getRoleName() {
		return rolename;
	}

	public void setRoleName(String rolename) {
		this.rolename = rolename;
	}

	public int getRoleCode() {
		return rolecode;
	}

	public void setRoleCode(int rolecode) {
		this.rolecode = rolecode;
	}

	@Override
	public String toString() {
		return "Role [rolename=" + rolename + ", rolecode=" + rolecode + "]";
		
	}	
	
}
