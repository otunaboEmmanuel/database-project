package com.example.database_project.controller;

import com.example.database_project.entities.Admin;
import com.example.database_project.entities.LoginDto;
import com.example.database_project.entities.Responses;
import com.example.database_project.entities.Users;
import com.example.database_project.repository.UsersRepository;
import com.example.database_project.service.UserService;
import com.example.database_project.service.UserServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("/users")
public class UsersController {
    static String DIRECTORY_PATH = "/u01/uploads/";
    @Autowired
    private UserService usersService;
    @Autowired
    private UsersRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserServiceImp userServiceImp;
    @PostMapping("/save")
    public ResponseEntity<?> saveUsers(@RequestBody Users users) {
        Responses responses = new Responses();
        Users userResult = usersService.saveUsers(users);
        return (userResult == null) ? new ResponseEntity<>(new Responses("100", "email or username already in use"), HttpStatus.OK) : new ResponseEntity<>(new Responses("00", "user saved successfully"), HttpStatus.OK);
    }
    @PostMapping("/register")
    public ResponseEntity<?>RegisterUser(@RequestBody Users user){
        Responses responses=new Responses();
        Users users=usersService.RegisterUser(user);
        return (users == null) ? new ResponseEntity<>(new Responses("100", "email  already in use"), HttpStatus.OK) : new ResponseEntity<>(new Responses("00", "user saved successfully, check your email for credentials"), HttpStatus.OK);
    }
    @PostMapping("/login")
    public ResponseEntity<?> LoginUser(@RequestBody LoginDto loginDto) {
        Users login = userRepository.findByEmail(loginDto.getEmail()).orElse(null);
        if (login != null) {
            String inputPassword = loginDto.getPassword();
            String storedPassword = login.getPassword();// Assuming this is stored as plain text
            Boolean isPwdRight = passwordEncoder.matches(inputPassword, storedPassword);

            if (isPwdRight) {
                return new ResponseEntity<>(new Responses("00", "Login success"), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(new Responses("111", "Password doesn't match"), HttpStatus.OK);
            }
        } else {
            return new ResponseEntity<>(new Responses("111", "Email doesn't exist"), HttpStatus.OK);
        }
    }
    @PostMapping("/delete")
    public String deleteUser(@RequestBody Users users){
        usersService.deleteUser(users.getId());
        return "deleted successfully";
    }
    @PostMapping("/add")
    public ResponseEntity<?> addUser(@RequestParam(value = "attachments", required = false) MultipartFile file,
                                     Users users,
                                     @RequestParam("firstname") String firstName,
                                     @RequestParam("lastname") String lastName,
                                     @RequestParam("password") String password,
                                     @RequestParam("email") String email) throws IOException{
      users.setEmail(email);
      users.setFirstName(firstName);
      users.setPassword(password);
      users.setLastName(lastName);
        Map<String, String> uploadResponse=usersService.uploadImageToFileSystem(file,users);
       return ResponseEntity.status(HttpStatus.OK)
               .body(uploadResponse);
    }
    @PostMapping("/downloadRequest")
    public ResponseEntity<?> downloadImageFromFileSystem(@RequestBody Map<String, String> data) throws IOException {
        // Retrieve the user by the provided requestId (or email)
        Users user = userRepository.findById(Integer.valueOf(data.get("id"))).orElse(null);

        if (user == null || user.getFilepath() == null) {
            // If the user or file path is not found, return a 404 Not Found response
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User or image not found");
        }

        // Retrieve the image data from the file system using the file path stored for the user
        byte[] imageData = usersService.downloadImageFromFileSystem(user.getFilepath());

        // Determine the content type (MIME type) of the image (e.g., "image/jpeg")
        String contentType = user.getType();  // Assumes the 'type' field stores the MIME type

        // Return the image data in the response, along with the correct content type
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.valueOf(contentType))  // Set the appropriate content type
                .body(imageData);  // Return the image binary data
    }

  @PostMapping("/update")
    public ResponseEntity<?>update(@RequestParam(value = "attachments", required = false) MultipartFile file,
                                   Users users,
                                   @RequestParam("firstname") String firstName,
                                   @RequestParam("lastname") String lastName,
                                   @RequestParam("password") String password,
                                   @RequestParam("email") String email,
                                   @RequestParam ("userId") String id ) throws IOException{
      Users users1=userRepository.findById(Integer.valueOf(id)).orElse(null);
      if (users1!=null){
          users1.setEmail(email);
          users1.setFirstName(firstName);
          users1.setPassword(password);
          users1.setLastName(lastName);
          if (file != null) {
              String filePath = userServiceImp.saveFileToStorage(file);
              users1.setFilepath(DIRECTORY_PATH + filePath);
              users1.setType(file.getContentType());
              users1.setName(file.getOriginalFilename());
          }
          userRepository.save(users1);
          return new ResponseEntity<>(new Responses("00","successfully updated"),HttpStatus.OK);
      }else
          return new ResponseEntity<>(new Responses("99","id not found"),HttpStatus.OK);
    }
@PostMapping("/deleted")
public String delete(@RequestBody Users users){
        Users users1=userRepository.findById(users.getId()).orElse(null);
        if (users1==null){
            return "user doesn't exist";
        }else
            userRepository.deleteById(users.getId());
        return "successfully deleted";
 }
@PostMapping("/getId")
    public ResponseEntity<?> findId(@RequestBody Users users){
        Users users1=userRepository.findById(users.getId()).orElse(null);
        if (users1!=null){
            return new ResponseEntity<>(users1,HttpStatus.OK);
        }else
            return new ResponseEntity<>(new Responses("99","id could not be found"),HttpStatus.EXPECTATION_FAILED);
}
    @GetMapping("/allusers")
    public List<Users> allBook(){
        List<Users> books=userRepository.findAll();
        return books;
    }
}

