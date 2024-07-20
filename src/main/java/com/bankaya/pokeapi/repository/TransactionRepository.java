package com.bankaya.pokeapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bankaya.pokeapi.entity.Transaction;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long>{}
