package com.jorder.bank.repository;

import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import static org.assertj.core.api.Assertions.assertThat;

import com.jorder.bank.model.User;

import jakarta.persistence.EntityManager;

@DataJpaTest
@ActiveProfiles("test")
public class UserRepositoryTest {

    @Autowired
    EntityManager entityManager;

    @Autowired
    UserRepository userRepository;

    private User CreateUser (User user) {
        this.entityManager.persist(user);
        return user;
    }

    @Test
    @DisplayName("Should get User successfully from database")
    void findUserByEmailCase1(){
        String email = "james@email.com";
        User data = new User(
            null, 
            "James", 
            email,
            "super_secure", 
            0, 
            null, 
            "some"
        );
        this.CreateUser(data);
        Optional<User> result = this.userRepository.findByEmail(email);
        
        assertThat(result.isPresent()).isTrue();
    }

    @Test
    @DisplayName("Should not get User from database when email doesnt exist")
    void findUserByEmailCase2(){
        String email = "james@email.com";
        
        Optional<User> result = this.userRepository.findByEmail(email);
        
        assertThat(result.isEmpty()).isTrue();
    }

}
