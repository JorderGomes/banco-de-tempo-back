package com.jorder.bank.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jorder.bank.model.Talent;
import com.jorder.bank.repository.TalentRepository;

@Service
public class TalentService {

    @Autowired
    TalentRepository talentRepository;

    public List<Talent> getTalents() {
        return talentRepository.findAll();
    }

    public Optional<Talent> getTalentById(Long id) {
        return talentRepository.findById(id);
    }

    public Talent createTalent(Talent user) {
        return talentRepository.save(user);
    }

    public Talent editTalent(Long id, Talent user) {
        if (!talentRepository.existsById(id)){
            return null;
        }
        user.setId(id);
        user = talentRepository.save(user);
        return user;
    }

    public void deleteTalent(Long id) {
        talentRepository.deleteById(id);
    }

}
