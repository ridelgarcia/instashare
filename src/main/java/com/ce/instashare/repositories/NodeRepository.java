package com.ce.instashare.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ce.instashare.model.Node;

@Repository
public interface NodeRepository extends JpaRepository<Node, String>{
	
	public List<Node> getByNameAndParentId(String name,String parentid);
	public List<Node> getByParentId(String parentid);
}
