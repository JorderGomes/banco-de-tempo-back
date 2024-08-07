package com.jorder.bank.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jorder.bank.model.Talent;
import com.jorder.bank.model.User;

@Repository
public interface TalentRepository extends JpaRepository<Talent, Long>{
    
    List<Talent> findByUser(User user);

}
