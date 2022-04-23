package com.ce.instashare.model;

import java.time.Instant;

import javax.persistence.*;

import org.hibernate.annotations.*;

@MappedSuperclass
public abstract class BaseEntity {

	@Id
	@GeneratedValue(generator = "custom-generator", 
			strategy = GenerationType.IDENTITY)
	@GenericGenerator(
			name = "custom-generator",
			strategy = "com.ce.instashare.model.BaseIdentifierGenerator")
	protected String id;

	@CreationTimestamp
	@Column(name = "created_at", updatable = false, nullable = false)
	protected Instant createdAt;

	@UpdateTimestamp
	@Column(name = "modified_at")
	protected Instant modifiedAt;

	@Column
	@Version
	protected int version;
	
	

	public BaseEntity() {
		
	}


	public BaseEntity(String id, Instant createdAt, Instant modifiedAt, int version) {
		this.id = id;
		this.createdAt = createdAt;
		this.modifiedAt = modifiedAt;
		this.version = version;
	}


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public Instant getCreatedAt() {
		return createdAt;
	}


	public void setCreatedAt(Instant createdAt) {
		this.createdAt = createdAt;
	}


	public Instant getModifiedAt() {
		return modifiedAt;
	}


	public void setModifiedAt(Instant modifiedAt) {
		this.modifiedAt = modifiedAt;
	}


	public int getVersion() {
		return version;
	}


	public void setVersion(int version) {
		this.version = version;
	}	
}
