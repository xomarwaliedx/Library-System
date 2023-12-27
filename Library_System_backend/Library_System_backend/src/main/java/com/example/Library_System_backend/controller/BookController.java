package com.example.Library_System_backend.controller;

import com.example.Library_System_backend.dto.BookDTO;
import com.example.Library_System_backend.dto.UserDTO;
import com.example.Library_System_backend.service.BookService;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin
@RequestMapping("/api/admin/books")
@RestController
@Setter
public class BookController {

  @Autowired
  private BookService bookService;

  @PostMapping("/addBook")
  public ResponseEntity<Boolean> addBook(@RequestBody BookDTO bookDTO) {
    bookService.addBook(bookDTO);
    return ResponseEntity.ok(true);
  }

  @GetMapping("/getBook/{bookId}")
  public ResponseEntity<String> getBookNameById(@PathVariable Long bookId) {
    String bookName = bookService.getBookNameById(bookId);
    if (bookName != null) {
      return ResponseEntity.ok(bookName);
    } else {
      return ResponseEntity.notFound().build();
    }
  }

  @GetMapping("/getAllBooks")
  public ResponseEntity<List<BookDTO>> getAllBooks() {
    List<BookDTO> allBooks = bookService.getAllBooks();
    return ResponseEntity.ok(allBooks);
  }

}
