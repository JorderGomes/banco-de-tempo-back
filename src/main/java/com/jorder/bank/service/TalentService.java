package com.jorder.bank.service;

// import java.time.LocalTime;
// import java.util.ArrayList;
// import java.util.HashMap;
// import java.util.Map;
// import java.util.stream.Collectors;
// import com.jorder.bank.model.Schedule;
// import com.jorder.bank.model.dto.TalentsAvaliableDto;

import java.util.Optional;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jorder.bank.model.Talent;
import com.jorder.bank.model.User;
import com.jorder.bank.model.enums.Category;
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

    public Talent editTalent(Long id, Talent newTalent) {
        if (!talentRepository.existsById(id)){
            return null;
        }
        newTalent.setId(id);
        Talent talent = talentRepository.findById(id).get();
        newTalent.setUser(talent.getUser());
        newTalent = talentRepository.save(newTalent);
        return newTalent;
    }

    public void deleteTalent(Long id) {
        talentRepository.deleteById(id);
    }

    public List<Talent> getTalentsByUser(Long userId) {
        var optUser = this.userRepository.findById(userId);
        if (!optUser.isPresent()) {
            return null;
        }
        return this.talentRepository.findByUser(optUser.get());
    }

    public List<Talent> getTalentsByCategoryAndName(String category, String name) {
        Category categoryEnum = this.convertStringToCategory(category);
        return this.talentRepository.findByCategoryAndNameContaining(categoryEnum, name);
    }

    public List<Talent> getTalentsByCategory(String category){
        Category categoryEnum = this.convertStringToCategory(category);
        return this.talentRepository.findByCategory(categoryEnum);
    }

    public Category convertStringToCategory(String category){
        try {
            return Category.valueOf(category.toUpperCase());
        } catch (Exception e) {
            return null;
        }
    }

}
