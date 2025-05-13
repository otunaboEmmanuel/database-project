package com.example.database_project.service;

import com.example.database_project.entities.Users;
import com.example.database_project.repository.UsersRepository;
import helpers.RandomGenerator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
@Slf4j
public class UserServiceImp implements UserService {
    static String DIRECTORY_PATH = "/u01/uploads/";
    @Autowired
    private UsersRepository usersRepository;


    @Autowired
    private PasswordEncoder bcryptEncoder;

    @Autowired
    private EmailService emailService;

    @Override
    public Users saveUsers(Users user) {
        Users user1 = usersRepository.findByEmail(user.getEmail()).orElse(null);
        //user.setPassword(bcryptEncoder.encode(user.getPassword()));
        return (user1 == null) ? usersRepository.save(user) : null;
    }

    @Override
    public Users RegisterUser(Users user) {

            // Check if the email already exists
            Users user1 = usersRepository.findByEmail(user.getEmail()).orElse(null);
            if (user1 != null) {
                // Email already exists; return null or handle the conflict
                return null;
            }

            // Email does not exist, proceed with registration
            String rawPassword = RandomGenerator.generateRandomString("");
            String hashed_password = BCrypt.hashpw(rawPassword, BCrypt.gensalt());
            user.setPassword(hashed_password);

            try {
                String htmlContent = "<html><body>" +
                        "<p>Hi " + user.getFirstName() + ",</p>" +
                        "<p>You have been successfully registered on my test app. </p>" +
                        "<p>Please use your Username as your email and  your password is given below to login </p>" +
                        "<p><b>Password: " + rawPassword + "</b></p>" +
                        "<p> See below for more details </p>" +
                        "</body></html>";
               // String htmlContent = "<html><body><p>Test Email</p></body></html>";
                MailBody mailBody = new MailBody(user.getEmail(), "Registration info", "This is your info " + htmlContent);
                emailService.sendSimpleMessage(mailBody);
            } catch (Exception e) {
                System.out.println("Error sending email: " + e.getMessage());
            }

            // Save the user and return
            return usersRepository.save(user);
        }

    @Override
    public void deleteUser(Integer id) {
        usersRepository.deleteById(id);
    }

    @Override
    public Map<String, String> uploadImageToFileSystem(MultipartFile file, Users users) {
        Map<String, String> responseData = new HashMap<>();
        Users users1=usersRepository.findByEmail(users.getEmail()).orElse(null);
         if(users1!=null) {
             if (file != null) {
                 String filePath = saveFileToStorage(file);
                 users.setFilepath(DIRECTORY_PATH + filePath);
                 users.setType(file.getContentType());
                 users.setName(file.getOriginalFilename());
             }

             Users storeInfo = usersRepository.save(users);
             if (storeInfo != null) {
                 responseData.put("code", "00");
                 responseData.put("message", "Request saved successfully");
                 responseData.put("requestId", String.valueOf(storeInfo.getId()));
             } else {
                 responseData.put("code", "90");
                 responseData.put("message", "Failed to save request");
             }

         } else {
            responseData.put("code", "99");
            responseData.put("message", "Invalid user email");
        }
        return responseData;
    }

    @Override
    public byte[] downloadImageFromFileSystem(String filepath) throws IOException {
        // Read the image from the file system and return as a byte array
        byte[] images = Files.readAllBytes(new File(filepath).toPath());
        return images;
    }




    public String saveFileToStorage(MultipartFile file) {

        String extensionType = file.getContentType();

        String extension = "";

        if (extensionType != null && !extensionType.isEmpty()) {
            String[] parts = extensionType.split("/");
            if (parts.length > 1) {
                extension = "." + parts[1];
            }
        }
        String fileName = UUID.randomUUID().toString().replace("-", "") + extension;

        try {
            File directory = new File(DIRECTORY_PATH);
            if (!directory.exists()) {
                directory.mkdirs();
            }
            File outputFile = new File(DIRECTORY_PATH + fileName);

            FileOutputStream outputStream = new FileOutputStream(outputFile);
            outputStream.write(file.getBytes());
            outputStream.close();

            log.info("File saved successfully to: " + outputFile.getAbsolutePath());
        } catch (IOException e) {
            log.info("Error saving file: " + e.getMessage());
        }
        return fileName;
    }

//    @Override
//    public Users RegisterUser(Users user) {
//        Users user1 = usersRepository.findByEmail(user.getEmail()).orElse(null);
//        if (user1 == null) {
//            String rawPassword = RandomGenerator.generateRandomString("");
//            String hashed_password = BCrypt.hashpw(rawPassword, BCrypt.gensalt());
//            user.setPassword(hashed_password);
//            user1.setFirstName(user.getFirstName());
//            user1.setLastName(user.getLastName());
//            user1.setEmail(user.getEmail());
//            try {
//                String htmlContent = "<html><body>" +
//                        "<p>Hi " + user1.getFirstName() + ",</p>" +
//                        "<p>You have been profiled successfully on Gian Carlo Auditioning app. </p>" +
//                        "<p>Please use your Username and password given below to login </p>"
//                        +"<p><b>Password: " + user1.getPassword()  +"</b></p>"+
//                        "<p> See below for more details </p>"+
//                        "</body></html>";
//
//                com.basicproject.project.service.MailBody mailBody = new com.basicproject.project.service.MailBody(user.getEmail(), "Registration info", "This is your info " + htmlContent);
//                emailService.sendSimpleMessage(mailBody);
//            }
//            catch (Exception e) {
//                System.out.println(e.getMessage());
//            }
//            return usersRepository.save(user);
//        }else {
//            return null;
//        }
//
//    }
    }