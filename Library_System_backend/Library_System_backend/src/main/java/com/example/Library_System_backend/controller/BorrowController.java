package com.example.Library_System_backend.controller;

import com.example.Library_System_backend.dto.BorrowDTO;
import com.example.Library_System_backend.service.BorrowService;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@CrossOrigin
@RequestMapping("/api")
@RestController
@Setter
public class BorrowController {

    @Autowired
    private BorrowService borrowService;

    @PostMapping("/user/borrowBook")
    public ResponseEntity<Boolean> borrowBook(@RequestBody BorrowDTO borrowDTO) {
        boolean result = borrowService.borrowBook(borrowDTO);

        if (result) {
            return ResponseEntity.ok(true);
        } else {
            return ResponseEntity.ok(false);
        }
    }


    @PostMapping("/user/returnBook")
    public ResponseEntity<Boolean> returnBook(@RequestBody BorrowDTO borrowDTO) {
        boolean isComplete=borrowService.returnBook(borrowDTO);
        return ResponseEntity.ok(isComplete);
    }

    @GetMapping("/borrowedBooks")
    public ResponseEntity<List<BorrowDTO>> getAllBorrowedBooks() {
        List<BorrowDTO> borrowedBooks = borrowService.getAllBorrowedBooks();
        return ResponseEntity.ok(borrowedBooks);
    }

    @GetMapping("/user/borrowedBooks/{userId}")
    public ResponseEntity<List<BorrowDTO>> getBooksBorrowedByUser(@PathVariable Long userId) {
        List<BorrowDTO> borrowedBooksByUser = borrowService.getBooksBorrowedByUser(userId);
        return ResponseEntity.ok(borrowedBooksByUser);
    }
}