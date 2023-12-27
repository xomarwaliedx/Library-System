package com.example.Library_System_backend.service;

import com.example.Library_System_backend.dto.BookDTO;
import com.example.Library_System_backend.entity.Book;
import com.example.Library_System_backend.mapper.Mapper;
import com.example.Library_System_backend.repository.BookRepository;
import com.example.Library_System_backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookService {

  private final Mapper mapper;
  private final BookRepository bookRepository;

  public void addBook(BookDTO bookDTO) {
    Book foundBook = bookRepository.findByBookName(bookDTO.getBookName());

    if (foundBook!=null) {
      foundBook.setCount(foundBook.getCount() + bookDTO.getCount());
      bookRepository.save(foundBook);
    } else {
      bookRepository.save(mapper.bookDTOToBook(bookDTO));
    }
  }


  public String getBookNameById(Long bookId) {
    Optional<Book> bookOptional = bookRepository.findById(bookId);
    return bookOptional.map(Book::getBookName).orElse(null);
  }

  public List<BookDTO> getAllBooks() {
    List<Book> books = bookRepository.findAll();
    return books.stream()
            .map(mapper::bookToBookDTO)
            .collect(Collectors.toList());
  }
}
