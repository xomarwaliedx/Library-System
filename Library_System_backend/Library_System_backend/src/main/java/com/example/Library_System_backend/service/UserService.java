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
  public List<UserDTO> test() {
    User user = new User("email", "1234", "fullName", "password",true);
    User found=userRepository.findByEmail("email");
    if (found!=null){
        System.err.println("User already exists");
    } else {
      userRepository.save(user);
    }

    List<User> output=userRepository.findAll();
    System.err.println("Hello World");
    return mapper.userListToUserDTOList(output);
  }
}
