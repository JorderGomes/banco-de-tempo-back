package com.jorder.bank.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jorder.bank.model.Schedule;
import com.jorder.bank.repository.ScheduleRepository;

@Service
public class ScheduleService {

    @Autowired
    ScheduleRepository scheduleRepository;

    public List<Schedule> getSchedules() {
        return scheduleRepository.findAll();
    }

    public Optional<Schedule> getScheduleById(Long id) {
        return scheduleRepository.findById(id);
    }

    public Schedule createSchedule(Schedule schedule) {
        return scheduleRepository.save(schedule);
    }

    public Schedule editSchedule(Long id, Schedule schedule) {
        if (!scheduleRepository.existsById(id)){
            return null;
        }
        schedule.setId(id);
        schedule = scheduleRepository.save(schedule);
        return schedule;
    }

    public void deleteSchedule(Long id) {
        scheduleRepository.deleteById(id);
    }

}
