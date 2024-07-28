package com.bankaya.pokeapi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bankaya.pokeapi.entity.Transaction;
import com.bankaya.pokeapi.repository.TransactionRepository;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Service
public class TransactionService {
	
	@Autowired
    private TransactionRepository transactionRepository;
	
	public Transaction createTransaction(HttpServletRequest request, HttpServletResponse response) {
		long startTime = System.nanoTime();
		
		Transaction transaction = new Transaction();
		transaction.setOriginIp( request.getRemoteAddr().toString() );
		transaction.setRequestDate( System.currentTimeMillis() );
		transaction.setMethod( request.getMethod() );
		
		transaction.setRequest( request.getRequestURL().toString() );
		transaction.setResponse( Long.toString(response.getStatus()) );
		long endTime = System.nanoTime();
		long activeTime = endTime - startTime;
		transaction.setActiveTime( String.valueOf(activeTime) );
		/*
		System.out.println("ip: " + request.getRemoteAddr().toString());
		System.out.println("request date : " + new Date(System.currentTimeMillis()));
		System.out.println("method : " + request.getMethod());
		
		System.out.println("active time : " + activeTime);
		System.out.println("request : " + request.getRequestURL().toString());
		System.out.println("response : " + response.getStatus());
		*/
        return transactionRepository.save(transaction);
    }

    public List<Transaction> getAllTransactions() {
        return transactionRepository.findAll();
    }
	
}
