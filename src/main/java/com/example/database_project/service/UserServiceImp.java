package com.example.database_project.service;

import com.example.database_project.entities.Users;
import com.example.database_project.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImp implements UserService {
    @Autowired
    private UsersRepository usersRepository;


    @Autowired
    private PasswordEncoder bcryptEncoder;
    @Override
    public Users saveUsers(Users user) {
        Users user1 = usersRepository.findByEmail(user.getEmail()).orElse(null);
        user.setPassword(bcryptEncoder.encode(user.getPassword()));
        return (user1==null )? usersRepository.save(user):null;
    }
}