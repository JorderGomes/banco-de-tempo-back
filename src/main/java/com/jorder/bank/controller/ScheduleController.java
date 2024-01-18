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
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.jorder.bank.model.Schedule;
import com.jorder.bank.repository.ScheduleRepository;
import com.jorder.bank.service.ScheduleService;

@RestController
@RequestMapping("/schedule")
public class ScheduleController {
    
    @Autowired
    private ScheduleService scheduleService;

    @Autowired
    private ScheduleRepository scheduleRepository;

    @GetMapping
    public ResponseEntity<List<Schedule>> getSchedules(){
        return ResponseEntity.ok(scheduleService.getSchedules());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Schedule> getSchedule(@PathVariable Long id){
        return scheduleService.getScheduleById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Schedule postSchedule(@RequestBody Schedule schedule){
        return scheduleService.createSchedule(schedule);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteSchedule(@PathVariable Long id){
        if (!scheduleRepository.existsById(id)){
            return ResponseEntity.notFound().build();
        }
        scheduleRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Schedule> updateSchedule(@RequestBody Schedule schedule, @PathVariable Long id){
        Schedule updatedSchedule = scheduleService.editSchedule(id, schedule);
        if (updatedSchedule == null){
            return ResponseEntity.notFound().build();
        }
        return  ResponseEntity.ok(updatedSchedule);
    }

}