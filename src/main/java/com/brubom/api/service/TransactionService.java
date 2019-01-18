package com.brubom.api.service;

import com.brubom.api.model.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author brunomoreira
 * NOT a real db, of course, just a quick in memory collection/cache implementation. Although it says
 * on exercise that this should be a prod ready code, using a nosql database or at least a
 * caching mechanism is a must.
 */
@Service
public class TransactionService {


    @Autowired
    private StatisticsService statisticsDb;

    public void clear() {

        statisticsDb.clear();
    }


    public void add(Transaction transaction) {

        statisticsDb.updateStatistics(transaction);

    }
}
