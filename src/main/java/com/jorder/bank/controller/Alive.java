package com.jorder.bank.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalTime;

import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/alive")
public class Alive {

    @GetMapping("")
    public String alive() {
        return "Alive";
    }

}
