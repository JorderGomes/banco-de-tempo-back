package com.jorder.bank.model.dto;

import java.time.LocalTime;

public interface TalentsAvaliableProjection {
    
    String getName();
    
    String getDescription();
    
    String getWeekDay();
    
    LocalTime getTimeBeguin();
    
    LocalTime getTimeEnd();
    
    int getQtdHours();

}
