package com.account.management.service;

import com.account.management.dto.TransactionRequest;
import com.account.management.dto.TransactionResponse;
import com.account.management.model.Transactions;

import java.util.List;


public interface TransactionsService {

    TransactionResponse createTransaction(TransactionRequest transactionRequest);

    List<Transactions> getTransactionsByAccountNumber(String accountNumber) throws InterruptedException;

    List<Transactions> getAllTransactions();

    List<Transactions> findByOrderByCreatedAtDesc();


}
