package com.example.database_project.service;

import com.example.database_project.entities.Admin;
import com.example.database_project.entities.Users;
import com.example.database_project.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImp implements AdminService{
    @Autowired
    private AdminRepository adminRepository;
    @Autowired
    private PasswordEncoder bcryptEncoder;
    @Override
    public Admin saveAdmins(Admin admin) {
        Admin admin1=adminRepository.findByEmail(admin.getEmail()).orElse(null);
        admin.setPassword(bcryptEncoder.encode(admin.getPassword()));
        return (admin1==null)?adminRepository.save(admin):null;
    }
}
