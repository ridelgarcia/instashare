package com.ce.instashare.model;

import java.time.Instant;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "[storage]")
public class Storage extends BaseEntity {
	
	@OneToOne
	private User user;
	
	@OneToOne
	private Node rootNode;

	public Storage() {
		super();
	}

	public Storage(String id, Instant createdAt, Instant modifiedAt, int version,User user, Node rootNode) {
		super(id,createdAt,modifiedAt,version);
		this.user = user;
		this.rootNode = rootNode;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Node getRootNode() {
		return rootNode;
	}

	public void setRootNode(Node rootNode) {
		this.rootNode = rootNode;
	}

	@Override
	public String toString() {
		return "Storage [user=" + user + ", rootNode=" + rootNode + "]";
	}
}
