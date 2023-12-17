package com.example.Library_System_backend.service;

import com.example.Library_System_backend.dto.UserDTO;
import com.example.Library_System_backend.entity.User;
import com.example.Library_System_backend.mapper.Mapper;
import com.example.Library_System_backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

  private final Mapper mapper;
  private final UserRepository userRepository;

  @Transactional
  public List<UserDTO> test() {

    List<User> output = userRepository.findAll();
    System.err.println("Hello World");
    return mapper.userListToUserDTOList(output);
  }

  @Transactional
  public UserDTO registerNewUser(UserDTO userDTO) {
    // Validate email format
    if (!isValidEmailFormat(userDTO.getEmail())) {
      throw new IllegalArgumentException("Invalid email format");
    }

    // Check if email is already in use
    if (userRepository.findByEmail(userDTO.getEmail()) != null) {
      throw new IllegalArgumentException("Email already exists");
    }

    // Save the new user
    User user = mapper.userDTOToUser(userDTO);
    userRepository.save(user);

    return mapper.userToUserDTO(user);
  }

  private boolean isValidEmailFormat(String email) {
    return email != null && email.contains("@");
  }
  
  @Transactional
  public UserDTO login(String email, String password, Boolean isAdmin) {
    // Find the user by email and password
    User user = userRepository.findByEmailAndPassword(email, password);
    if (user == null || (isAdmin!=user.isAdmin())) {
      throw new IllegalArgumentException("Invalid data");
    }

    return mapper.userToUserDTO(user);
  }
}
