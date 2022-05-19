package com.ce.instashare.dto.storage.response;

import java.time.Instant;

public class NodeResponseDTO {
	
	public enum NodeType{
		FOLDER,
		FILE
	}
	private String id;
	
	private String name;
	
	private NodeType type;	
	
	private Instant createdAt;

	private Instant modifiedAt;

	public NodeResponseDTO() {
		
	}

	public NodeResponseDTO(String id, String name, NodeType type, Instant createdAt, Instant modifiedAt) {
		super();
		this.id = id;
		this.name = name;
		this.type = type;
		this.createdAt = createdAt;
		this.modifiedAt = modifiedAt;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public NodeType getType() {
		return type;
	}

	public void setType(NodeType type) {
		this.type = type;
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

	@Override
	public String toString() {
		return "NodeResponseDTO [id=" + id + ", name=" + name + ", type=" + type + ", createdAt=" + createdAt
				+ ", modifiedAt=" + modifiedAt + "]";
	}	
}
