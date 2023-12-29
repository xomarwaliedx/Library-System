package com.example.Library_System_backend.controller;

import com.example.Library_System_backend.dto.BookDTO;
import com.example.Library_System_backend.dto.UserDTO;
import com.example.Library_System_backend.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RequestMapping("/api")
@RestController
public class UserController {

  @Autowired
  private UserService userService;

  @PostMapping("/register")
  public ResponseEntity<String> registerUser(@RequestBody UserDTO userDTO) {
    try {
      UserDTO registeredUser = userService.registerNewUser(userDTO);
      return ResponseEntity.status(HttpStatus.CREATED).body("User registered successfully");
    } catch (IllegalArgumentException e) {
      return ResponseEntity.badRequest().body(e.getMessage());
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
              .body("Unexpected error during user registration: " + e.getMessage());
    }
  }

  @PostMapping("/user/users/login")
  public ResponseEntity<Object> loginUser(@RequestBody UserDTO userDTO) {
    try {
      UserDTO loggedInUser = userService.login(userDTO.getEmail(), userDTO.getPassword(), userDTO.getIsAdmin());
      return ResponseEntity.ok(loggedInUser);
    } catch (IllegalArgumentException e) {
      // Check if the exception message indicates invalid credentials
      if ("Invalid credentials".equals(e.getMessage())) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid email or password");
      } else {
        // Handle other bad requests
        return ResponseEntity.badRequest().body(e.getMessage());
      }
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
              .body("Unexpected error during user login: " + e.getMessage());
    }
  }

  @GetMapping("/admin/getAllUsers")
  public ResponseEntity<List<UserDTO>> getAllBooks() {
    List<UserDTO> allUsers = userService.getAllUsers();
    return ResponseEntity.ok(allUsers);
  }

}
