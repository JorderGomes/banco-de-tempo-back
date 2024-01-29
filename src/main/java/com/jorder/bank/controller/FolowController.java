package com.jorder.bank.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.jorder.bank.model.Folow;
import com.jorder.bank.model.User;
import com.jorder.bank.repository.FolowRepository;
import com.jorder.bank.service.FolowService;

@RestController
@RequestMapping("/folow")
public class FolowController {

    @Autowired
    private FolowService folowService;

    @Autowired
    private FolowRepository folowRepository;

    // all
    @GetMapping
    public ResponseEntity<List<Folow>> getFolows() {
        return ResponseEntity.ok(folowService.getFolows());
    }

    // Seguidores - Folowers
    @GetMapping("/folowers/{id_folowed}")
    public ResponseEntity<List<User>> getFolowers(@PathVariable Long id_folowed) {
        return ResponseEntity.ok(folowRepository.findFolowersByFolowedId(id_folowed));
    }

    // Seguidos - Folowed
    @GetMapping("/folowed/{id_folower}")
    public ResponseEntity<List<User>> getFolowed(@PathVariable Long id_folower) {
        return ResponseEntity.ok(folowRepository.findFolowedByFolowerId(id_folower));
    }

    // Seguido
    @GetMapping("/{id_folowed}")
    public ResponseEntity<Folow> getFolow(@PathVariable Long id_folowed) {
        return folowService.getFolowById(id_folowed)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Folow postFolow(@RequestBody Folow folow) {
        return folowService.createFolow(folow);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteFolow(@PathVariable Long id) {
        if (!folowRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        folowRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
