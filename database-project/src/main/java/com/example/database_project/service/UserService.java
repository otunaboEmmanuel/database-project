package com.example.database_project.service;

import com.example.database_project.entities.Users;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

public interface UserService {
    Users saveUsers(Users users);

    Users RegisterUser(Users user);

    void deleteUser(Integer id);

    Map<String, String> uploadImageToFileSystem(MultipartFile file, Users users);

    byte[] downloadImageFromFileSystem(String filepath) throws IOException;



}
