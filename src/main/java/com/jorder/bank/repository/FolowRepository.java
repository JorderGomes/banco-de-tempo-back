package com.jorder.bank.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.jorder.bank.model.Folow;
import com.jorder.bank.model.User;

import java.util.List;


@Repository
public interface FolowRepository extends JpaRepository<Folow, Long> {
    
    @Query("select f.folower from Folow f where f.folowed.id = :folowed_id")
    List<User> findFolowersByFolowedId(@Param("folowed_id") Long folowed_id);

    @Query("select f.folowed from Folow f where f.folower.id = :folower_id")
    List<User> findFolowedByFolowerId(@Param("folower_id") Long folower_id);

}