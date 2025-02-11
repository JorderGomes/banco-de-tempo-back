package com.jorder.bank.service;

// import java.time.LocalTime;
// import java.util.ArrayList;
// import com.jorder.bank.model.dto.TalentsAvaliableDto;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jorder.bank.model.User;
import com.jorder.bank.repository.UserRepository;
import com.jorder.bank.util.PasswordEncoder;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    PasswordEncoder passwordEncoder = new PasswordEncoder();

    private static final Random random = new Random();

    // Auxiliar methods
    public String generateSalt(){
        return Integer.toString(Math.abs(random.nextInt()), 36).substring(0, 5);
    }

    // Busisness logic
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    public User createUser(User user) throws Exception {
        if (
            user.getEmail() == null || 
            user.getPassword() == null ||
            user.getName() == null
            ) {
            throw new Exception("Preencha todos os campos!");
        }
        String salt = this.generateSalt();
        user.setSalt(salt);
        String passwordSalt = user.getPassword().concat(salt);
        user.setPassword(passwordEncoder.encode(passwordSalt)); 

        return userRepository.save(user);
    }

    public User editUser(Long id, User user) throws Exception {
        User userDb = this.userRepository.findById(id)
            .orElseThrow(() -> new Exception("User not found"));
        
        user.setPassword(userDb.getPassword());
        user.setSalt(userDb.getSalt());
        user.setId(id);
        
        user = userRepository.save(user);
        return user;
    }

    public User updatePassword(Long id, String newPassword) throws Exception {
        User currentUser = this.userRepository.findById(id)
            .orElseThrow(() -> new Exception("User not found"));
    
        String salt = this.generateSalt(); 
        newPassword.concat(salt);
        currentUser.setSalt(salt);
        currentUser.setPassword(passwordEncoder.encode(newPassword));
        return this.userRepository.save(currentUser);
    }

    public void deleteUser(Long id) throws Exception {
        userRepository.findById(id)
            .orElseThrow(() -> new Exception("User not found"));
        userRepository.deleteById(id);
    }

    public User updateBalance(Long id, int amount) throws Exception {
        User currentUser = this.userRepository.findById(id)
            .orElseThrow(() -> new Exception("User not found"));

        if ((currentUser.getBalance() + amount) < 0){
            throw new Exception("Saldo insuficiente.");
        }
        
        currentUser.setBalance(currentUser.getBalance() + amount);
        return this.userRepository.save(currentUser);
    }

    // public List<User> getAvaliableTalents(
    //     String category, String weekDay, LocalTime timeBeguin
    //     ) {
    //         var result = this.userRepository.findAvaliableTalents(category, weekDay, timeBeguin); 
    //         return result;
    // }

}
