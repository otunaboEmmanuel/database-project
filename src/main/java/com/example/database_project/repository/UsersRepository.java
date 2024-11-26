package com.example.database_project.repository;

import com.example.database_project.entities.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsersRepository extends JpaRepository<Users,Integer> {

    Optional<Users> findByEmail(String email);
    Optional <Users> findByEmailAndPassword(String email, String Password);
}

