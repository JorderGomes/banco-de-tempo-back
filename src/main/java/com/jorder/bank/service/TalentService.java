package com.jorder.bank.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jorder.bank.model.Talent;
import com.jorder.bank.model.User;
import com.jorder.bank.repository.TalentRepository;
import com.jorder.bank.repository.UserRepository;

@Service
public class TalentService {

    @Autowired
    TalentRepository talentRepository;

    @Autowired
    UserRepository userRepository;

    public List<Talent> getTalents() {
        return talentRepository.findAll();
    }

    public Optional<Talent> getTalentById(Long id) {
        return talentRepository.findById(id);
    }

    public Talent createTalent(Talent talent, Long userId) {
        Optional<User> optUser = userRepository.findById(userId);
        if (!optUser.isPresent()){
            return null;
        }
        talent.setUser(optUser.get());
        return talentRepository.save(talent);
    }

    public Talent editTalent(Long id, Talent talent) {
        if (!talentRepository.existsById(id)){
            return null;
        }
        talent.setId(id);
        talent = talentRepository.save(talent);
        return talent;
    }

    public void deleteTalent(Long id) {
        talentRepository.deleteById(id);
    }

}
