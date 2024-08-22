package com.jorder.bank.repository;

// import java.time.LocalTime;
// import java.util.List;
// import com.jorder.bank.model.dto.UserTalentsDto;
// import org.springframework.data.jpa.repository.Query;

// import java.util.Optional;
// import org.springframework.data.jpa.repository.EntityGraph;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jorder.bank.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{


    

}
