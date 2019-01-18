package com.brubom.api.repository;

import com.brubom.api.model.StatisticsAggregate;
import com.brubom.api.model.Transaction;
import com.brubom.api.service.StatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class StatisticsRepository {

    @Autowired
    private StatisticsService statisticsDb;

    public void updateStatistics(Transaction transaction) {

        statisticsDb.updateStatistics(transaction);

    }


    public StatisticsAggregate getStatistics() {

        return statisticsDb.getStatistics();
    }
}
