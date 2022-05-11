package com.ce.instashare.model;

import java.time.Instant;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

@Entity
@Table(name = "[node]")
public class Node extends BaseEntity {
	
	public enum NodeType{
		FOLDER,
		FILE
	}
	
	@Null
	@Column(name = "name")
	private String name;
	
	@NotNull
	@Column(name = "type")
	private NodeType type;
	
	@Null
	@Column(name = "firstblockid")
	private String firstBlockId;
	
	@Null
	@Column(name = "parentid")
	private String parentId;

	public Node() {
		super();
	}

	public Node(String id, Instant createdAt, Instant modifiedAt,int version,@Null String name, @NotNull NodeType type, @Null String firstBlockId, @Null String parentId) {
		super(id,createdAt,modifiedAt,version);
		this.name = name;
		this.type = type;
		this.firstBlockId = firstBlockId;
		this.parentId = parentId;
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

	public String getFirstBlockId() {
		return firstBlockId;
	}

	public void setFirstBlockId(String firstBlockId) {
		this.firstBlockId = firstBlockId;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	@Override
	public String toString() {
		return "Node [name=" + name + ", type=" + type + ", firstBlockId=" + firstBlockId + ", parentId=" + parentId
				+ "]";
	}

}
