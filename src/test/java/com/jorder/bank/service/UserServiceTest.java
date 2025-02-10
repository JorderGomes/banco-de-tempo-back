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
    void createUserCase1(){
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
    @DisplayName("Should trow an Exception when try to delete an inexistent user")
    void deleteUserCase1(){
        // Given (cenário)
        Long userId = 999L; // ID de um usuário que não existe
        Mockito.when(userRepository.findById(userId)).thenReturn(Optional.empty());

        // When & Then (ação e verificação)
        assertThrows(Exception.class, () -> userService.deleteUser(userId));

        // Verifica se o método deleteById NUNCA foi chamado (pois o usuário não existe)
        Mockito.verify(userRepository, Mockito.never()).deleteById(userId);
    }

}
