package com.example.Library_System_backend.service;

import com.example.Library_System_backend.dto.UserDTO;
import com.example.Library_System_backend.entity.User;
import com.example.Library_System_backend.mapper.Mapper;
import com.example.Library_System_backend.repository.UserRepository;
import lombok.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

  private final Mapper mapper;
  private final UserRepository userRepository;


  @Transactional
  public UserDTO test() {
    User user = new User("email", "1234", "fullName", "password",true);
    userRepository.save(user);
    List<User> output=userRepository.findAll();
    System.err.println("Hello World");
    User output1 = output.get(0);
    UserDTO outputDTO = mapper.userToUserDTO(output1);
    return outputDTO;
  }



}
