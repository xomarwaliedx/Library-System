package com.example.Library_System_backend.databaseLoader;

import com.example.Library_System_backend.entity.User;
import com.example.Library_System_backend.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DatabaseLoader implements CommandLineRunner {

  private final UserRepository userRepository;

  public DatabaseLoader(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Override
  public void run(String... args) {
    // Check if the record exists by name
    User user = userRepository.findByEmail("admin@admin.com");

    if (user == null) {
      // Record doesn't exist, create and save a new one
      User newRecord = new User("admin@admin.com", "123456", "admin", "123456", true);

      userRepository.save(newRecord);
    }
  }
}
