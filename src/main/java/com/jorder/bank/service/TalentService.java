package com.jorder.bank.service;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jorder.bank.model.Schedule;
import com.jorder.bank.model.Talent;
import com.jorder.bank.model.User;
import com.jorder.bank.model.dto.TalentsAvaliableDto;
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

    public List<TalentsAvaliableDto> getAvaliableTalents(String category, String weekDay, LocalTime timeBeguin) {
        var talentsAvaliableProjection = this.talentRepository.findTalentsAvaliable(
            category, weekDay, timeBeguin
        );
        
        Map<String, TalentsAvaliableDto> talentsMap = new HashMap<String, TalentsAvaliableDto>();
        talentsAvaliableProjection.stream().map(currentTalent -> {
            if (!talentsMap.containsKey(currentTalent.getName().concat(currentTalent.getDescription()))) {
                List<Schedule> newSchedules = new ArrayList<Schedule>();
                newSchedules.add(Schedule.builder()
                .weekDay(currentTalent.getWeekDay())
                .timeBeguin(currentTalent.getTimeBeguin())
                .timeEnd(currentTalent.getTimeEnd())
                .qtdHours(currentTalent.getQtdHours())
                .build());
                TalentsAvaliableDto newTalentAvaliableDto = new TalentsAvaliableDto(
                    currentTalent.getName(),
                    currentTalent.getDescription(),
                    currentTalent.getUser(),
                    newSchedules
                );
                talentsMap.put(
                    currentTalent.getName().concat(currentTalent.getDescription()), 
                    newTalentAvaliableDto
                    );
            } else {
                talentsMap.get(currentTalent.getName().concat(currentTalent.getDescription()))
                    .getSchedules().add(Schedule.builder()
                        .weekDay(currentTalent.getWeekDay())
                        .timeBeguin(currentTalent.getTimeBeguin())
                        .timeEnd(currentTalent.getTimeEnd())
                        .qtdHours(currentTalent.getQtdHours())
                        .build()
                    );
            }
            return currentTalent;
        }).collect(Collectors.toList());
        return new ArrayList<TalentsAvaliableDto>(talentsMap.values());
    }

}
