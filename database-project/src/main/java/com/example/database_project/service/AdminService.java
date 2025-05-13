package com.example.database_project.service;

import com.example.database_project.entities.Admin;
import com.example.database_project.entities.Users;
import org.springframework.stereotype.Service;

import java.util.List;


public interface AdminService {
    Admin saveAdmins(Admin admin);

    List<Admin> getAllAdmin();
}
