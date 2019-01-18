package com.brubom.api.repository;

import com.brubom.api.model.Transaction;
import com.brubom.api.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TransactionRepository {

    @Autowired
    private TransactionService transactionDb;



    public Transaction create(Transaction transaction) {

        transactionDb.add(transaction);
        return transaction;
    }

    public void clear() {
        transactionDb.clear();
    }
}
