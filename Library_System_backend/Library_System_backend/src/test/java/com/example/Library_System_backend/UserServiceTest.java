package com.example.Library_System_backend;
import com.example.Library_System_backend.dto.UserDTO;
import com.example.Library_System_backend.entity.User;
import com.example.Library_System_backend.mapper.Mapper;
import com.example.Library_System_backend.repository.UserRepository;
import com.example.Library_System_backend.service.UserService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.Mockito.*;

@SpringBootTest
class UserServiceTest {

    @Mock
    private Mapper mapper;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @Test
    void registerNewUser_WithInvalidEmailFormat_ShouldThrowException() {
        UserDTO userDTO = new UserDTO("invalidEmail", "1234", "fullName", "password", true);
        assertThrows(IllegalArgumentException.class, () -> userService.registerNewUser(userDTO));

        // Ensure that save method is not called
        verify(userRepository, never()).save(any());
    }

    @Test
    void registerNewUser_WithExistingEmail_ShouldThrowException() {
        UserDTO userDTO = new UserDTO("existing@email.com", "1234", "fullName", "password", true);

        when(userRepository.findByEmail("existing@email.com")).thenReturn(new User());
        assertThrows(IllegalArgumentException.class, () -> userService.registerNewUser(userDTO));

        // Ensure that save method is not called
        verify(userRepository, never()).save(any());
    }

    @Test
    void registerNewUser_WithValidInput_ShouldSaveUser() {
        UserDTO userDTO = new UserDTO("new@email.com", "1234", "fullName", "password", true);

        when(userRepository.findByEmail("new@email.com")).thenReturn(null);
        when(mapper.userDTOToUser(userDTO)).thenReturn(new User());

        userService.registerNewUser(userDTO);

        // Ensure that save method is called once
        verify(userRepository, times(1)).save(any());
    }

    @Test
    void testNonExistentUser() {
        // Mocking repository to return null (user not found)
        when(userRepository.findByEmailAndPassword(anyString(), anyString())).thenReturn(null);

        // Test when the user does not exist
        assertThrows(IllegalArgumentException.class, () -> {
            userService.login("nonexistent@example.com", "password", false);
        });
    }

    @Test
    void testNonAdminUserLoginAsAdmin() {
        // Mocking repository to return a regular user
        User mockUser = new User();
        when(userRepository.findByEmailAndPassword(anyString(), anyString())).thenReturn(mockUser);

        // Mocking mapper to return a UserDTO
        UserDTO mockUserDTO = new UserDTO("test@example.com", "123456789", "John Doe", "password", false);
        when(mapper.userToUserDTO(any())).thenReturn(mockUserDTO);

        // Test when a non-admin user tries to log in as an admin
        assertThrows(IllegalArgumentException.class, () -> {
            userService.login("test@example.com", "password", true);
        });
    }

    @Test
    void testNullEmail() {
        // Test when the email is null
        assertThrows(IllegalArgumentException.class, () -> {
            userService.login(null, "password", false);
        });
    }

    @Test
    void testNullPassword() {
        // Test when the password is null
        assertThrows(IllegalArgumentException.class, () -> {
            userService.login("test@example.com", null, false);
        });
    }

    @Test
    void testNullEmailAndPassword() {
        // Test when both email and password are null
        assertThrows(IllegalArgumentException.class, () -> {
            userService.login(null, null, false);
        });
    }

    @Test
    @DisplayName("Valid Login")
    void testValidLogin() {
        // Mocking repository to return a user
        User mockUser = new User();
        when(userRepository.findByEmailAndPassword(anyString(), anyString())).thenReturn(mockUser);

        // Mocking mapper to return a UserDTO
        UserDTO mockUserDTO = new UserDTO("test@example.com", "123456789", "John Doe", "password", false);
        when(mapper.userToUserDTO(mockUser)).thenReturn(mockUserDTO);

        // Test the login method
        UserDTO result = userService.login("test@example.com", "password", false);

        // Assertions
        assertNotNull(result);
        assertEquals(mockUserDTO, result);
    }
}


