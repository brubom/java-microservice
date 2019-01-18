package com.brubom.api.controller;

import com.brubom.api.model.StatisticsAggregate;
import com.brubom.api.repository.StatisticsRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/statistics")
public class StatisticsController {


    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private StatisticsRepository statisticsRepository;

    @GetMapping
    public StatisticsAggregate getStatistics(){
        return  statisticsRepository.getStatistics();
    }

}
