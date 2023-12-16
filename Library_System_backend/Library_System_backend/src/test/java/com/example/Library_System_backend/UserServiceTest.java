package com.example.Library_System_backend;
import com.example.Library_System_backend.dto.UserDTO;
import com.example.Library_System_backend.entity.User;
import com.example.Library_System_backend.mapper.Mapper;
import com.example.Library_System_backend.repository.UserRepository;
import com.example.Library_System_backend.service.UserService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertThrows;
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
}


