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
}
