package com.example.Library_System_backend.mapper;

import com.example.Library_System_backend.dto.UserDTO;
import com.example.Library_System_backend.entity.User;
import org.mapstruct.ReportingPolicy;
import org.springframework.stereotype.Service;

import java.util.List;

@org.mapstruct.Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface Mapper {

  UserDTO userToUserDTO(User user);

  List<UserDTO> userListToUserDTOList(List<User> users);

  User userDTOToUser(UserDTO userDTO);

  List<User> userDTOListToUserList(List<UserDTO> userDTOs);


}
