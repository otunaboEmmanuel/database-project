package com.example.database_project.controller;

import com.example.database_project.entities.LoginDto;
import com.example.database_project.entities.Responses;
import com.example.database_project.entities.Users;
import com.example.database_project.repository.UsersRepository;
import com.example.database_project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("/users")
public class UsersController {
    @Autowired
    private UserService usersService;
    @Autowired
    private UsersRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @PostMapping("/save")
    public ResponseEntity<?> saveUsers(@RequestBody Users users) {
        Responses responses = new Responses();
        Users userResult = usersService.saveUsers(users);
        return (userResult == null) ? new ResponseEntity<>(new Responses("100", "email or username already in use"), HttpStatus.OK) : new ResponseEntity<>(new Responses("00", "user saved successfully"), HttpStatus.OK);
    }
    @PostMapping("/login")
    public ResponseEntity<?> LoginUser(@RequestBody LoginDto loginDto) {
        Users login = userRepository.findByEmail(loginDto.getEmail()).orElse(null);
        if (login != null) {
            String password = loginDto.getPassword();
            String encodedPassword = login.getPassword();
            Boolean isPwdRight = passwordEncoder.matches(password, encodedPassword);
            if (isPwdRight) {

                Optional<Users> user = userRepository.findByEmailAndPassword(login.getEmail(), encodedPassword);
                if (user.isPresent()) {

                    return new ResponseEntity<>(new Responses("00", "Login success"),HttpStatus.OK);
                } else {
                    return new ResponseEntity<>(new Responses("111", "login failed"), HttpStatus.OK);
                }
            } else {
                return new ResponseEntity<>(new Responses("111", "password doesn't match"), HttpStatus.OK);
            }
        } else {
            return new ResponseEntity<>(new Responses("111", "Email doesn't exist"), HttpStatus.OK);
        }
}
}

