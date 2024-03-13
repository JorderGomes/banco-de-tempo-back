package com.jorder.bank.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.jorder.bank.model.FavorRequest;

@Repository
public interface FavorRequestRepository extends JpaRepository<FavorRequest, Long> {

    @Query("SELECT f FROM FavorRequest f WHERE f.applicant.id = :applicantId")
    List<FavorRequest> findAllFavorRequestByApplicant(Long applicantId);

}
