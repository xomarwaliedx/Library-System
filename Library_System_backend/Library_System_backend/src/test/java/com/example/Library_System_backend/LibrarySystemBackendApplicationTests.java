package com.example.Library_System_backend;

import com.example.Library_System_backend.controller.UserController;
import com.example.Library_System_backend.dto.UserDTO;
import com.example.Library_System_backend.entity.User;
import static org.junit.jupiter.api.Assertions.*;

import com.example.Library_System_backend.mapper.Mapper;
import com.example.Library_System_backend.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
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
	public void testTestUsers() {
		List<UserDTO> expectedUserDTO = new ArrayList<>(); // Initializing an ArrayList
		expectedUserDTO.add(new UserDTO("email", "1234", "fullName", "password", true));

		when(userService.test()).thenReturn(expectedUserDTO);

		ResponseEntity<List<UserDTO>> responseEntity = userController.testUsers();

		assertEquals(expectedUserDTO, responseEntity.getBody());
		assertEquals(200, responseEntity.getStatusCodeValue());

		verify(userService, times(1)).test();
	}

}
