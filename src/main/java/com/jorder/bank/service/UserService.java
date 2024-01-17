package com.jorder.bank.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jorder.bank.model.User;
import com.jorder.bank.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    public User createUser(User user) {
        return userRepository.save(user);
    }

    public User editUser(Long id, User user) {
        if (!userRepository.existsById(id)){
            return null;
        }
        user.setId(id);
        user = userRepository.save(user);
        return user;
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

}
