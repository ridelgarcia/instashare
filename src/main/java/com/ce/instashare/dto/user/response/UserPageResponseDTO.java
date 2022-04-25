package com.ce.instashare.dto.user.response;

import java.util.List;

public class UserPageResponseDTO {

	int start;
	
	Long end;
	
	List<UserResponseDTO> users;
	
	int size;
	
	public UserPageResponseDTO() {
		this.start = 0;
		this.end = 0L;
		this.users = null;
		this.size = 0;
	}

	public UserPageResponseDTO(int start, Long end, List<UserResponseDTO> users, int size) {
		this.start = start;
		this.end = end;
		this.users = users;
		this.size = size;
	}

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public Long getEnd() {
		return end;
	}

	public void setEnd(Long end) {
		this.end = end;
	}

	public List<UserResponseDTO> getUsers() {
		return users;
	}

	public void setUsers(List<UserResponseDTO> users) {
		this.users = users;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	@Override
	public String toString() {
		return "UserPageResponseDTO [start=" + start + ", end=" + end + ", users=" + users + ", size=" + size + "]";
	}
	
	
}
