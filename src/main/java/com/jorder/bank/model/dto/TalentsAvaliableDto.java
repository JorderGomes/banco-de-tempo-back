package com.jorder.bank.model.dto;

import java.time.LocalTime;
// import java.util.List;
// import com.jorder.bank.model.Schedule;

// import lombok.AllArgsConstructor;
// import lombok.Builder;
// import lombok.Data;
// import lombok.NoArgsConstructor;

// @Data
// @Builder
// @NoArgsConstructor
// @AllArgsConstructor
public interface TalentsAvaliableDto {
    
    String getName();
    
    String getDescription();
    
    String getWeekDay();
    
    LocalTime getTimeBeguin();
    
    LocalTime getTimeEnd();
    
    int getQtdHours();

}
