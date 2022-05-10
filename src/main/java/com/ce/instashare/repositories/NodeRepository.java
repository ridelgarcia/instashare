package com.ce.instashare.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ce.instashare.model.Node;

@Repository
public interface NodeRepository extends JpaRepository<Node, String>{

}
