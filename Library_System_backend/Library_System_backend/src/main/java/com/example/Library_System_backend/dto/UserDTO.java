package com.example.Library_System_backend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UserDTO extends HasLongIdDTO {
  private String email;

  private String phone;

  private String fullName;

  private String password;

  private boolean isAdmin;

}
