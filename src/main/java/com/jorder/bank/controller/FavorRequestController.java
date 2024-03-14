package com.jorder.bank.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jorder.bank.model.FavorRequest;
import com.jorder.bank.model.StatusFavor;
import com.jorder.bank.repository.FavorRequestRepository;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/favor_request")
public class FavorRequestController {

    @Autowired
    private FavorRequestRepository favorRequestRepository;

    @GetMapping
    public ResponseEntity<List<FavorRequest>> getFavorRequestList() {
        return ResponseEntity.ok(favorRequestRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<FavorRequest> getFavorRequestById(@PathVariable Long id) {
        return favorRequestRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // todo: get All by User
    @GetMapping("/applicant/{id_applicant}")
    public List<FavorRequest> getFavorRequestsByApplicant(@PathVariable Long id_applicant) {
        return favorRequestRepository.findAllFavorRequestByApplicant(id_applicant);
    }

    // todo: get One by User

    // todo: post request favor
    @PostMapping("")
    public FavorRequest postFavorRequest( @RequestBody FavorRequest favorRequest) {
        favorRequest.setStatusFavor(StatusFavor.SOLICITADA);
        var createdFavorRequest = favorRequestRepository.save(favorRequest);
        return createdFavorRequest;
    }

    // todo: patch update favor
    @PatchMapping("/{id_favor}")
    public ResponseEntity<Object> updateStatusFavor(@RequestBody StatusFavor newStatus, @PathVariable Long id_favor) {
        var optFavorRequest = favorRequestRepository.findById(id_favor);
        if (optFavorRequest.isPresent()) {
            var favorRequest = optFavorRequest.get();
            try {
                favorRequest.updateStatus(newStatus);
            } catch (Exception e) {
                return ResponseEntity.badRequest().body(e.getMessage());
            }
            favorRequestRepository.save(favorRequest);
            return ResponseEntity.ok(favorRequest);
        }
        return ResponseEntity.notFound().build();
    }
}
