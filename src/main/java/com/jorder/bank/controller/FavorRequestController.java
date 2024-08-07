package com.jorder.bank.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jorder.bank.model.FavorRequest;
import com.jorder.bank.model.StatusFavor;
import com.jorder.bank.service.FavorRequestService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/favor_request")
public class FavorRequestController {

    @Autowired
    private FavorRequestService favorRequestService;

    @GetMapping
    public ResponseEntity<List<FavorRequest>> getFavorRequestList() {
        return ResponseEntity.ok(favorRequestService.getFavorRequests());
    }

    @GetMapping("/{id}")
    public ResponseEntity<FavorRequest> getFavorRequestById(@PathVariable Long id) {
        return favorRequestService.getFavorRequestById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/applicant/{id_applicant}")
    public List<FavorRequest> getFavorRequestsByApplicant(@PathVariable Long id_applicant) {
        return favorRequestService.getFavorRequestsByApplicant(id_applicant);
    }

    // todo: get One by User

    @PostMapping("")
    public ResponseEntity<Object> postFavorRequest( @RequestBody FavorRequest favorRequest) {
        try {
            var currentFavorRequest = favorRequestService.createFavorRequest(favorRequest);
            return ResponseEntity.ok(currentFavorRequest);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PatchMapping("/{id_favor}/update-status")
    public ResponseEntity<Object> updateStatusFavor(@RequestBody StatusFavor newStatus, @PathVariable Long id_favor) {
        try {
            var favorUpdated = favorRequestService.updateStatusFavor(newStatus, id_favor);
            if (favorUpdated == null) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(favorUpdated);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PatchMapping("/{id_favor}/update-qtd-hours")
    public ResponseEntity<Object> updateQtdHours(@PathVariable Long id_favor, @RequestParam int qtdHours){
        try {
            return ResponseEntity.ok(this.favorRequestService.updateQtdHours(id_favor, qtdHours));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());   
        }
    }

}
