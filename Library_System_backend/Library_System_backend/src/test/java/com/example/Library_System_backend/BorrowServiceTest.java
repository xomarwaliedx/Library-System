package com.example.Library_System_backend;

import com.example.Library_System_backend.dto.BorrowDTO;
import com.example.Library_System_backend.entity.Book;
import com.example.Library_System_backend.entity.Borrow;
import com.example.Library_System_backend.entity.User;
import com.example.Library_System_backend.mapper.Mapper;
import com.example.Library_System_backend.repository.BorrowRepository;
import com.example.Library_System_backend.service.BorrowService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class BorrowServiceTest {

    @Mock
    private Mapper mapper;

    @Mock
    private BorrowRepository borrowRepository;

    @InjectMocks
    private BorrowService borrowService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void getBooksBorrowedByUser() {
        Long userId = 101L;
        User user1 = new User();
        user1.setId(userId);
        user1.setEmail("user1@example.com");
        user1.setPhone("123456789");
        user1.setFullName("John Doe");
        user1.setPassword("password");
        user1.setIsAdmin(false);

        Book book1 = new Book();
        book1.setId(201L);
        book1.setBookName("Book 1");
        book1.setAuthor("Author 1");
        book1.setGenre("Genre 1");
        book1.setLanguage("English");
        book1.setPublicationYear(2020);
        book1.setCount(5);

        Book book2 = new Book();
        book2.setId(202L);
        book2.setBookName("Book 2");
        book2.setAuthor("Author 2");
        book2.setGenre("Genre 2");
        book2.setLanguage("Spanish");
        book2.setPublicationYear(2019);
        book2.setCount(3);

        Borrow borrow1 = new Borrow();
        borrow1.setId(1);
        borrow1.setBorrowDate(LocalDate.now());
        borrow1.setDuration(14);
        borrow1.setUser(user1);
        borrow1.setBook(book1);

        Borrow borrow2 = new Borrow();
        borrow2.setId(2);
        borrow2.setBorrowDate(LocalDate.now());
        borrow2.setDuration(21);
        borrow2.setUser(user1);
        borrow2.setBook(book2);

        // Borrow by another user
        User user2 = new User();
        user2.setId(102L);
        user2.setEmail("user2@example.com");
        user2.setPhone("987654321");
        user2.setFullName("Jane Doe");
        user2.setPassword("password");
        user2.setIsAdmin(false);

        Book book3 = new Book();
        book3.setId(203L);
        book3.setBookName("Book 3");
        book3.setAuthor("Author 3");
        book3.setGenre("Genre 3");
        book3.setLanguage("French");
        book3.setPublicationYear(2018);
        book3.setCount(7);

        Borrow borrow3 = new Borrow();
        borrow3.setId(3);
        borrow3.setBorrowDate(LocalDate.now());
        borrow3.setDuration(10);
        borrow3.setUser(user2); // This borrow is for a different user
        borrow3.setBook(book3);

        List<Borrow> borrowedBooksByUser = Arrays.asList(borrow1, borrow2);

        when(borrowRepository.findByUser_Id(userId)).thenReturn(borrowedBooksByUser);

        BorrowDTO borrowDTO1 = new BorrowDTO();
        borrowDTO1.setId(1);
        borrowDTO1.setBorrowDate(LocalDate.now());
        borrowDTO1.setDuration(14);
        borrowDTO1.setUserId(userId);
        borrowDTO1.setBookId(201L);

        BorrowDTO borrowDTO2 = new BorrowDTO();
        borrowDTO2.setId(2);
        borrowDTO2.setBorrowDate(LocalDate.now());
        borrowDTO2.setDuration(21);
        borrowDTO2.setUserId(userId);
        borrowDTO2.setBookId(202L);

        List<BorrowDTO> expectedDTOs = Arrays.asList(borrowDTO1, borrowDTO2);

        when(mapper.borrowToBorrowDTO(borrow1)).thenReturn(borrowDTO1);
        when(mapper.borrowToBorrowDTO(borrow2)).thenReturn(borrowDTO2);

        // Test
        List<BorrowDTO> result = borrowService.getBooksBorrowedByUser(userId);

        // Verify
        assertEquals(expectedDTOs, result);
        verify(borrowRepository, times(1)).findByUser_Id(userId);
        verify(mapper, times(borrowedBooksByUser.size())).borrowToBorrowDTO(any());
    }

}

