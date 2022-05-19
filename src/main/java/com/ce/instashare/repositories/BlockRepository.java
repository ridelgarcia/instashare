package com.ce.instashare.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ce.instashare.model.Block;

@Repository
public interface BlockRepository extends JpaRepository<Block, String> {

}
