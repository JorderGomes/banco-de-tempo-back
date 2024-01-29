package com.jorder.bank.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jorder.bank.model.Folow;
import com.jorder.bank.repository.FolowRepository;

@Service
public class FolowService {

    @Autowired
    private FolowRepository folowRepository;

    public List<Folow> getFolows() {
        return folowRepository.findAll();
    }

    public Optional<Folow> getFolowById(Long id) {
        return folowRepository.findById(id);
    }

    public Folow createFolow(Folow folow) {
        return folowRepository.save(folow);
    }

    public Folow editFolow(Long id, Folow folow) {
        if (!folowRepository.existsById(id)) {
            return null;
        }
        folow.setId(id);
        folow = folowRepository.save(folow);
        return folow;
    }

    public void deleteFolow(Long id) {
        folowRepository.deleteById(id);
    }

}
