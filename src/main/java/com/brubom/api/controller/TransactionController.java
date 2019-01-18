package com.brubom.api.controller;

import com.brubom.api.exception.DeserializationException;
import com.brubom.api.exception.SerializationException;
import com.brubom.api.model.Transaction;
import com.brubom.api.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    @Autowired
    private TransactionRepository transactionRepository;

    private static final int SECONDS_STAT = 60;

    @RequestMapping

    public void addTransaction(@RequestBody Transaction transaction, HttpServletResponse response){

        if((System.currentTimeMillis() - transaction.getTimestamp()) / 1000 >= SECONDS_STAT ){

            response.setStatus(HttpStatus.NO_CONTENT.value());
            return;

        }

        if(transaction.getTimestamp() > System.currentTimeMillis()){
            response.setStatus(HttpStatus.UNPROCESSABLE_ENTITY.value());
            return;
        }

        transactionRepository.create(transaction);
        response.setStatus(HttpStatus.CREATED.value());
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAll(){

        transactionRepository.clear();
    }

    @ExceptionHandler(SerializationException.class)
    public void serializationErrorHandler(HttpServletResponse response) {
        response.setStatus(HttpStatus.UNPROCESSABLE_ENTITY.value());
    }


    @ExceptionHandler(DeserializationException.class)
    public void deserializationErrorHandler(DeserializationException error, HttpServletResponse response) {

        if(error.getErrorCode() == DeserializationException.ErrorCodes.INVALID_JSON)
            response.setStatus(HttpStatus.BAD_REQUEST.value());

        if(error.getErrorCode() == DeserializationException.ErrorCodes.INVALID_FIELD_VALUE)
            response.setStatus(HttpStatus.UNPROCESSABLE_ENTITY.value());
    }

}
