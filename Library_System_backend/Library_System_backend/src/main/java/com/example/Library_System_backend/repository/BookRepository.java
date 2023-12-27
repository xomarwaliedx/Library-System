package com.example.Library_System_backend.repository;

import com.example.Library_System_backend.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
    Book findByBookName(String bookName);
}
