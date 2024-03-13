package com.jorder.bank.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.jorder.bank.model.Talent;
import com.jorder.bank.repository.TalentRepository;
import com.jorder.bank.service.TalentService;

@RestController
@RequestMapping("/talent")
public class TalentController {
    
    @Autowired
    private TalentService talentService;

    @Autowired
    private TalentRepository talentRepository;

    @GetMapping
    public ResponseEntity<List<Talent>> getTalents(){
        return ResponseEntity.ok(talentService.getTalents());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Talent> getTalent(@PathVariable Long id){
        return talentService.getTalentById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/{userId}")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Talent> postTalent(
        @RequestBody Talent talent, 
        @PathVariable Long userId
        ){
        Talent savedTalent = talentService.createTalent(talent, userId);
        if (savedTalent == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(savedTalent);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteTalent(@PathVariable Long id){
        if (!talentRepository.existsById(id)){
            return ResponseEntity.notFound().build();
        }
        talentRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Talent> updateTalent(
        @RequestBody Talent talent, 
        @PathVariable Long id
        ){
        Talent updatedTalent = talentService.editTalent(id, talent);
        if (updatedTalent == null){
            return ResponseEntity.notFound().build();
        }
        return  ResponseEntity.ok(updatedTalent);
    }

}