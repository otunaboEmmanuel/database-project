package com.example.database_project.repository;

import com.example.database_project.entities.Admin;
import com.example.database_project.entities.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Integer> {
    Optional<Admin> findByEmail(String email);
    Optional <Admin> findByEmailAndPassword(String email, String Password);
}
