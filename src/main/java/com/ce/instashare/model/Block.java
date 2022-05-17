package com.ce.instashare.model;

import java.time.Instant;
import java.util.Arrays;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Null;


@Entity
@Table(name = "[block]")
public class Block  extends BaseEntity{

	@Null
	@Column(name = "data",length=16384)	
	private byte[] data;
	
	@Null
	@Column(name = "nextid")
	private String nextId;

	public Block() {
		super();
	}

	public Block(String id, Instant createdAt, Instant modifiedAt,int version,byte[] data, String nextId) {
		super(id,createdAt,modifiedAt,version);
		this.data = data;
		this.nextId = nextId;
	}

	public byte[] getData() {
		return data;
	}

	public void setData(byte[] data) {
		this.data = data;
	}

	public String getNextId() {
		return nextId;
	}

	public void setNextId(String nextId) {
		this.nextId = nextId;
	}

	@Override
	public String toString() {
		return "Block [data=" + Arrays.toString(data) + ", nextId=" + nextId + "]";
	}	
}
