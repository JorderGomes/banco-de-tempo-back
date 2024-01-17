package com.jorder.bank.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jorder.bank.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
    
}
