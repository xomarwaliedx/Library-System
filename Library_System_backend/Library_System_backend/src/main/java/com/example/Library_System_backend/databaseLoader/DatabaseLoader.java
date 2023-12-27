package com.example.Library_System_backend.databaseLoader;

import com.example.Library_System_backend.entity.User;
import com.example.Library_System_backend.mapper.Mapper;
import com.example.Library_System_backend.repository.BookRepository;
import com.example.Library_System_backend.repository.BorrowRepository;
import com.example.Library_System_backend.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class DatabaseLoader implements CommandLineRunner {

    private final UserRepository userRepository;
    private final BookRepository bookRepository;

    private final Mapper mapper;
    private final BorrowRepository borrowRepository;


    public DatabaseLoader(UserRepository userRepository,
                          BookRepository bookRepository,Mapper mapper,
                          BorrowRepository borrowRepository) {
        this.userRepository = userRepository;
        this.bookRepository = bookRepository;
        this.mapper = mapper;
        this.borrowRepository = borrowRepository;
    }

    @Override
    @Transactional
    public void run(String... args) {

        User user = userRepository.findByEmail("admin@admin.com");

        if (user == null) {
            User newRecord = new User("admin@admin.com", "123456", "admin", "123456", true, null);

            userRepository.save(newRecord);
        }
    }
}
