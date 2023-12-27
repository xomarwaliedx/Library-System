package com.example.Library_System_backend.entity;

import javax.persistence.*;

import lombok.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Table(name = "user", uniqueConstraints = @UniqueConstraint(columnNames = {"email", "phone"}))
public class User extends HasLongId {

  @Column(name = "email", unique = true, nullable = false)
  private String email;

  @Column(name = "phone", nullable = false)
  private String phone;

  @Column(name = "full_name", nullable = false)
  private String fullName;

  @Column(name = "password", nullable = false)
  private String password;

  @Column(name = "is_admin", nullable = false)
  private Boolean isAdmin;
}
