package com.ce.instashare.model;

import javax.persistence.*;

@Entity
@Table(name = "[user]")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "name")
	private String name;

	@Column(name = "lastname")
	private String lastname;

	@Column(name = "email")
	private String email;

    @Column(name = "password")
	private String password;

    @Column(name = "role")
	private Integer role;


	public User() {

	}

	public User(String name, String lastname, String email, String password,Integer role) {
		this.name = name;
        this.lastname = lastname;
        this.email = email;
        this.password = password;
        this.role = role;
	}

	public Long getId() {
		return this.id;
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
    public Integer getRole() {
		return role;
	}

	public void setRole(Integer role) {
		this.role = role;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", lastname=" + lastname + ", email=" + email + ", role=" + role + "]";
	}
}

