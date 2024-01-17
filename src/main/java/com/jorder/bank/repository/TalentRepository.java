package com.jorder.bank.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jorder.bank.model.Talent;

@Repository
public interface TalentRepository extends JpaRepository<Talent, Long>{
    
}
