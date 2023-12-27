package com.example.Library_System_backend.service;


import com.example.Library_System_backend.dto.BorrowDTO;
import com.example.Library_System_backend.entity.Book;
import com.example.Library_System_backend.entity.Borrow;
import com.example.Library_System_backend.mapper.Mapper;
import com.example.Library_System_backend.repository.BookRepository;
import com.example.Library_System_backend.repository.BorrowRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BorrowService {

    private final Mapper mapper;

    private final BorrowRepository borrowRepository;
    private final BookRepository bookRepository;

    @Autowired
    UserService userService;
    public List<BorrowDTO> getAllBorrowedBooks() {
        List<Borrow> borrowedBooks = borrowRepository.findAll();
        return borrowedBooks.stream()
                .map(mapper::borrowToBorrowDTO)
                .collect(Collectors.toList());
    }

    public List<BorrowDTO> getBooksBorrowedByUser(Long userId) {
        List<Borrow> borrowedBooksByUser = borrowRepository.findByUser_Id(userId);
        return borrowedBooksByUser.stream()
                .map(mapper::borrowToBorrowDTO)
                .collect(Collectors.toList());
    }
    
}