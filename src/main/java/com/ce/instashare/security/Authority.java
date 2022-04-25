package com.ce.instashare.security;

import org.springframework.security.core.GrantedAuthority;

public class Authority implements GrantedAuthority {


	private static final long serialVersionUID = 4825309589515721658L;
	
	private String authority;
	
	
	
	public Authority(String authority) {
		this.authority = authority;
	}



	public String getAuthority() {
		return authority;
	}
}
