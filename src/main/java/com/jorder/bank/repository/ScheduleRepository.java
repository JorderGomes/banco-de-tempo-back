package com.jorder.bank.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jorder.bank.model.Schedule;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Long>{
    
}
