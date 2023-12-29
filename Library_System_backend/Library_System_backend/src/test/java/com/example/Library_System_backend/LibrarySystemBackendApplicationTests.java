package com.example.Library_System_backend;

import com.example.Library_System_backend.controller.BookController;
import com.example.Library_System_backend.controller.UserController;
import com.example.Library_System_backend.dto.BookDTO;
import com.example.Library_System_backend.dto.UserDTO;
import com.example.Library_System_backend.entity.User;
import static org.junit.jupiter.api.Assertions.*;

import com.example.Library_System_backend.mapper.Mapper;
import com.example.Library_System_backend.service.BookService;
import com.example.Library_System_backend.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

@SpringBootTest
class LibrarySystemBackendApplicationTests {

	@Mock
	private UserService userService; // Mocking UserService

	@InjectMocks
	private UserController userController; // Injecting mocked dependencies into UserController

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this); // Initialize annotated mocks
	}
	@Test
	void contextLoads() {
	}


	@Test
	public void testAddBookEndpoint() {
		// Create a mock BookService
		BookService bookService = Mockito.mock(BookService.class);

		BookController bookController = new BookController();
		bookController.setBookService(bookService);

		BookDTO bookDTO = new BookDTO();
		bookDTO.setBookName("Hamada Book");
		bookDTO.setAuthor("3amoora taratatara");
		bookDTO.setGenre("Fiction");
		bookDTO.setLanguage("English");
		bookDTO.setPublicationYear(2015);
		bookDTO.setCount(4);

		ResponseEntity<Boolean> response = bookController.addBook(bookDTO);

		// Verify that the BookService's addBook method was called
		Mockito.verify(bookService, Mockito.times(1)).addBook(bookDTO);

		// Verify the response from the endpoint
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(true, response.getBody());
	}


}
