package com.ce.instashare.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ce.instashare.model.Storage;
import com.ce.instashare.model.User;

@Repository
public interface StorageRepository extends JpaRepository<Storage, String> {

	public Storage getByUser(User s);
}
