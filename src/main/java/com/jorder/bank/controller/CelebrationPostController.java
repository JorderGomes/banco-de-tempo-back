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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.jorder.bank.model.CelebrationPost;
import com.jorder.bank.repository.CelebrationPostRepository;
import com.jorder.bank.service.CelebrationPostService;

@RestController
@RequestMapping("/celebration-post")
public class CelebrationPostController {

    @Autowired
    private CelebrationPostService celebrationPostService;

    @Autowired
    private CelebrationPostRepository celebrationPostRepository;

    @GetMapping
    public ResponseEntity<List<CelebrationPost>> getCelebrationPosts() {
        return ResponseEntity.ok(celebrationPostService.getCelebrationPosts());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CelebrationPost> getCelebrationPost(@PathVariable Long id) {
        return celebrationPostService.getCelebrationPostById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<CelebrationPost> postCelebrationPost(
            @RequestBody CelebrationPost celebrationPost,
            @RequestParam Long userId) {
        CelebrationPost savedCelebrationPost = celebrationPostService.createCelebrationPost(celebrationPost, userId);
        if (savedCelebrationPost == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(savedCelebrationPost);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteCelebrationPost(@PathVariable Long id) {
        if (!celebrationPostRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        celebrationPostRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<CelebrationPost> updateCelebrationPost(@RequestBody CelebrationPost celebrationPost,
            @PathVariable Long id) {
        CelebrationPost updatedCelebrationPost = celebrationPostService.editCelebrationPost(id, celebrationPost);
        if (updatedCelebrationPost == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedCelebrationPost);
    }

}