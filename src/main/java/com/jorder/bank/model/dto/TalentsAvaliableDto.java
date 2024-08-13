package com.jorder.bank.model.dto;

import java.util.List;

import com.jorder.bank.model.Schedule;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TalentsAvaliableDto {
    
    private String name;
    
    private String description;

    private List<Schedule> schedules;

}
