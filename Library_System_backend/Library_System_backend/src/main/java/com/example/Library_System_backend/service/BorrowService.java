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

    public boolean borrowBook(BorrowDTO borrowDTO) {
        // Map the BorrowDTO to Borrow entity
        Borrow borrow = mapper.borrowDTOToBorrow(borrowDTO);
        // Set the borrowDate to the current date
        borrow.setBorrowDate(LocalDate.now());
        // Retrieve the book from the database
        Book book = bookRepository.findById(borrowDTO.getBookId()).orElse(null);

        // Check if the book exists and its count is greater than 0
        if (book != null && book.getCount() > 0) {
            // Decrement the count in the book table
            book.setCount(book.getCount() - 1);
            bookRepository.save(book);

            // Save the Borrow record
            borrowRepository.save(borrow);

            // Notify the user
            System.out.println("Book is reserved to you. Head to the library to pick it up.");
            return true;
        } else {
            // Book is not available
            System.out.println("Book not available now.");
            return false;
        }
    }

    public boolean returnBook(BorrowDTO borrowDTO) {
        Integer id=borrowDTO.getId();
        Optional<Borrow> optionalBorrow = borrowRepository.findById(id);

        if (optionalBorrow.isPresent()) {
            Borrow borrow = optionalBorrow.get();

            // Calculate return date and penalty
            LocalDate borrowDate = borrow.getBorrowDate();
            int duration = borrow.getDuration();
            LocalDate returnDate = borrowDate.plusDays(duration);
            LocalDate currentDate = LocalDate.now();

            long lateDays = currentDate.isAfter(returnDate) ? currentDate.toEpochDay() - returnDate.toEpochDay() : 0;
            double penalty = lateDays * 10; // $10 penalty per late day

            // Display or use the penalty as needed
            System.out.println("Return Date: " + returnDate);
            System.out.println("Current Date: " + currentDate);
            System.out.println("Penalty: $" + penalty);

            // Delete the Borrow record
            borrowRepository.deleteByIdIn(id);
            // Increment the count field in the associated book
            Book associatedBook = borrow.getBook();
            if (associatedBook != null) {
                associatedBook.setCount(associatedBook.getCount() + 1);
                bookRepository.save(associatedBook);
            }

            return true;
        } else {
            // Handle the case where the Borrow record with the given ID is not found
            System.out.println("Borrow record not found with ID: " + id);
            return false;
        }
    }
}