package com.example.Library_System_backend.controller;

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

  private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

  @GetMapping("/user/users")
  public ResponseEntity<List<UserDTO>> testUsers() {
    List<UserDTO> userDTO = userService.test();
    return ResponseEntity.ok(userDTO);
  }

  @PostMapping("/register")
  public ResponseEntity<String> registerUser(@RequestBody UserDTO userDTO) {
    try {
      UserDTO registeredUser = userService.registerNewUser(userDTO);
      return ResponseEntity.status(HttpStatus.CREATED).body("User registered successfully");
    } catch (IllegalArgumentException e) {
      LOGGER.warn("Bad request during user registration: {}", e.getMessage());
      return ResponseEntity.badRequest().body(e.getMessage());
    } catch (Exception e) {
      LOGGER.error("Unexpected error during user registration", e);
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
        LOGGER.warn("Bad request during user login: {}", e.getMessage());
        return ResponseEntity.badRequest().body(e.getMessage());
      }
    } catch (Exception e) {
      LOGGER.error("Unexpected error during user login", e);
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
              .body("Unexpected error during user login: " + e.getMessage());
    }
  }

}
