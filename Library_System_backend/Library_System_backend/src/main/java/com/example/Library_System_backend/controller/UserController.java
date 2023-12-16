package com.example.Library_System_backend.controller;



import com.example.Library_System_backend.dto.UserDTO;
import com.example.Library_System_backend.entity.User;
import com.example.Library_System_backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/user/users")
@RestController
public class UserController {

  @Autowired
  private UserService userService;

  @GetMapping("")
  public ResponseEntity<List<UserDTO>> testUsers() {
    List<UserDTO> userDTO = userService.test();
    return ResponseEntity.ok(userDTO);
  }

}
