package com.ce.instashare.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ce.instashare.model.Role;


@Repository
public interface RoleRepository extends JpaRepository<Role, String> {
    
}