package com.jorder.bank.service;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

// import java.util.Random;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;

import com.jorder.bank.model.User;
import com.jorder.bank.repository.UserRepository;
import com.jorder.bank.util.PasswordEncoder;

public class UserServiceTest {

    @Mock
    UserRepository userRepository;

    @Mock
    PasswordEncoder passwordEncoder;

    @Autowired
    @InjectMocks
    UserService userService;

    @BeforeEach
    void setup(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Should create User successfully when data has all fields")
    void createUserCase1() throws Exception{
        User data = User.builder()
        .id(1L)
        .name("James")
        .email("james@email.com")
        .password("super_secure")
        .balance(100)
        .build();

        // when(random.nextInt()).thenReturn(-1083188604);    
        when(passwordEncoder.encode("super_secure")).thenReturn("51bef0011ac941e1d301c76a03167cb037e517e0fce3ddb00074caf1c6533656");  
        when(userRepository.save(data)).thenReturn(data);  

        userService.createUser(data);

        verify(userRepository, times(1)).save(any());
    }

    @Test
    @DisplayName("Should throw an Exception when name is missing")
    void createUserCase2() {
        User user = User.builder() 
        .email("james@email.com")
        .password("super_secure")
        .build();

        assertThrows(Exception.class, () -> userService.createUser(user));
    }

    @Test
    @DisplayName("Should throw an Exception when email is missing")
    void createUserCase3() {
        User user = User.builder()
        .name("James")
        .password("super_secure")
        .build();

        assertThrows(Exception.class, () -> userService.createUser(user));
    }

    @Test
    @DisplayName("Should throw an Exception when password is missing")
    void createUserCase4() {
        User user = User.builder()
        .name("James")
        .email("james@email.com")
        .build();

        assertThrows(Exception.class, () -> userService.createUser(user));
    }
    

    @Test
    @DisplayName("Should trow an Exception when try to delete an inexistent user")
    void deleteUserCase1(){
        Long userId = 999L; 
        Mockito.when(userRepository.findById(userId)).thenReturn(Optional.empty());

        assertThrows(Exception.class, () -> userService.deleteUser(userId));

        Mockito.verify(userRepository, Mockito.never()).deleteById(userId);
    }

}
