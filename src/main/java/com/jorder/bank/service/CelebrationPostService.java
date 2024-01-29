package com.jorder.bank.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jorder.bank.model.CelebrationPost;
import com.jorder.bank.model.User;
import com.jorder.bank.repository.CelebrationPostRepository;
import com.jorder.bank.repository.UserRepository;

@Service
public class CelebrationPostService {

    @Autowired
    CelebrationPostRepository celebrationPostRepository;

    @Autowired
    UserRepository userRepository;

    public List<CelebrationPost> getCelebrationPosts() {
        return celebrationPostRepository.findAll();
    }

    public Optional<CelebrationPost> getCelebrationPostById(Long id) {
        return celebrationPostRepository.findById(id);
    }

    public CelebrationPost createCelebrationPost(CelebrationPost celebrationPost, Long userId) {
        Optional<User> optUser = userRepository.findById(userId);
        if (!optUser.isPresent()) {
            return null;
        }
        celebrationPost.setUser(optUser.get());
        celebrationPost.setPublicationDate(LocalDateTime.now());
        return celebrationPostRepository.save(celebrationPost);
    }

    public CelebrationPost editCelebrationPost(Long id, CelebrationPost celebrationPost) {
        if (!celebrationPostRepository.existsById(id)) {
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
