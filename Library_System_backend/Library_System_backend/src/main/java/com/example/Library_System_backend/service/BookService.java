package com.example.Library_System_backend.service;

import com.example.Library_System_backend.dto.BookDTO;
import com.example.Library_System_backend.mapper.Mapper;
import com.example.Library_System_backend.repository.BookRepository;
import com.example.Library_System_backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class BookService {

  private final Mapper mapper;

  private final BookRepository bookRepository;

  public void addBook(BookDTO bookDTO) {
    bookRepository.save(mapper.bookDTOToBook(bookDTO));
  }
}
