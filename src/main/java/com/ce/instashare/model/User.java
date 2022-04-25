package com.ce.instashare.model;

import java.time.Instant;

import javax.persistence.*;

@Entity
@Table(name = "[user]")
public class User extends BaseEntity {	

	@Column(name = "name")
	private String name;

	@Column(name = "lastname")
	private String lastname;

	@Column(name = "email")
	private String email;

    @Column(name = "password")
	private String password;

    
    @ManyToOne
	private Role role;


	public User() {
		super();
	}

	public User(String id, Instant createdAt, Instant modifiedAt, int version,String name, String lastname, String email, String password,Role role) {
		super(id,createdAt,modifiedAt,version);
		this.name = name;
        this.lastname = lastname;
        this.email = email;
        this.password = password;
        this.role = role;
	}

	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

    public String getLastName() {
		return lastname;
	}

	public void setLastName(String lastname) {
		this.lastname = lastname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

    public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
    public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}
	@Override
	public String toString() {
		return "User [name=" + name + ", lastname=" + lastname + ", email=" + email + ", role=" + role.toString() + "]";
	}
}

