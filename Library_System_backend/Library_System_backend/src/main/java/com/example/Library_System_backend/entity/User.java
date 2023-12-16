package com.example.Library_System_backend.entity;

import javax.persistence.*;

import lombok.*;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name="user", uniqueConstraints = @UniqueConstraint(columnNames = {"email", "phone"}))
public class User extends HasLongId {

  @Column(name = "email", unique = true, nullable = false)
  private String email;

  @Column(name = "phone", nullable = false)
  private String phone;

  @Column(name = "full_name", nullable = false)
  private String fullName;

  private String password;

  private boolean isAdmin;
}
