package com.jorder.bank.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jorder.bank.model.CelebrationPost;
import com.jorder.bank.repository.CelebrationPostRepository;

@Service
public class CelebrationPostService {

    @Autowired
    CelebrationPostRepository celebrationPostRepository;

    public List<CelebrationPost> getCelebrationPosts() {
        return celebrationPostRepository.findAll();
    }

    public Optional<CelebrationPost> getCelebrationPostById(Long id) {
        return celebrationPostRepository.findById(id);
    }

    public CelebrationPost createCelebrationPost(CelebrationPost celebrationPost) {
        return celebrationPostRepository.save(celebrationPost);
    }

    public CelebrationPost editCelebrationPost(Long id, CelebrationPost celebrationPost) {
        if (!celebrationPostRepository.existsById(id)){
            return null;
        }
        celebrationPost.setId(id);
        celebrationPost = celebrationPostRepository.save(celebrationPost);
        return celebrationPost;
    }

    public void deleteCelebrationPost(Long id) {
        celebrationPostRepository.deleteById(id);
    }

}
