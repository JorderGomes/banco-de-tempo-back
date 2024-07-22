package com.jorder.bank.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jorder.bank.model.Talent;
import com.jorder.bank.model.User;

@Repository
public interface TalentRepository extends JpaRepository<Talent, Long>{
    
    /*
     @Query("select f.folower from Folow f where f.folowed.id = :folowed_id")
    List<User> findFolowersByFolowedId(@Param("folowed_id") Long folowed_id);
    */
    List<Talent> findByUser(User user);

}
