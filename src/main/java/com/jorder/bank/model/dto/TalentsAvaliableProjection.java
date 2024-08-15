package com.jorder.bank.model.dto;

import java.time.LocalTime;

import com.jorder.bank.model.User;

public interface TalentsAvaliableProjection {
    
    String getName();
    
    String getDescription();

    User getUser();
    
    String getWeekDay();
    
    LocalTime getTimeBeguin();
    
    LocalTime getTimeEnd();
    
    int getQtdHours();

}
