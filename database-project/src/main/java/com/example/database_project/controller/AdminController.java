package com.example.database_project.controller;

import com.example.database_project.entities.Admin;
import com.example.database_project.entities.LoginDto;
import com.example.database_project.entities.Responses;
import com.example.database_project.entities.Users;
import com.example.database_project.repository.AdminRepository;
import com.example.database_project.service.AdminService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private AdminService adminService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/save")
    public ResponseEntity<?> saveAdmin(@RequestBody Admin admin) {
        Responses responses = new Responses();
        Admin adminResult = adminService.saveAdmins(admin);
        return (adminResult == null) ? new ResponseEntity<>(new Responses("100", "email or username already in use"), HttpStatus.OK) : new ResponseEntity<>(new Responses("00", "user saved successfully"), HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<?> LoginAdmin(@RequestBody LoginDto loginDto) {
        Admin login = adminRepository.findByEmail(loginDto.getEmail()).orElse(null);
        if (login != null) {
            String inputPassword = loginDto.getPassword();
            String storedPassword = login.getPassword(); // Assuming this is stored as plain text

            if (inputPassword.equals(storedPassword)) {
                return new ResponseEntity<>(new Responses("00", "Login success"), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(new Responses("111", "Password doesn't match"), HttpStatus.OK);
            }
        } else {
            return new ResponseEntity<>(new Responses("111", "Email doesn't exist"), HttpStatus.OK);
        }
    }

    @GetMapping("/allAdmins")
    public List<Admin> getAll(){
        return adminService.getAllAdmin();
    }
}
