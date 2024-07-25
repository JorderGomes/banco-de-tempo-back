package com.jorder.bank.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jorder.bank.model.Schedule;
import com.jorder.bank.model.User;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Long>{

    List<Schedule> findByUser(User user);
    
}
