package com.ce.instashare.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ce.instashare.model.User;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    public List<User> getByName(String name);
    public List<User> getByEmail(String email);
    public List<User> findByEmailAndPassword(String email,String password);
}
