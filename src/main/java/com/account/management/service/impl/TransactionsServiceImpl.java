package com.account.management.service.impl;


import com.account.management.Repository.TransactionRepository;
import com.account.management.dto.TransactionRequest;
import com.account.management.dto.TransactionResponse;
import com.account.management.model.Transactions;
import com.account.management.service.TransactionsService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;


@Service
@AllArgsConstructor
@Slf4j
public class TransactionsServiceImpl implements TransactionsService {

    private final TransactionRepository transactionRepository;


    @Override
    public TransactionResponse createTransaction(TransactionRequest transactionRequest) {

        log.info("Calling create transaction service...");


            Transactions transactions = Transactions.builder()
                    .name(transactionRequest.getName())
                    .phone(transactionRequest.getPhone())
                    .accountNumber(transactionRequest.getAccountNumber())
                    .depositAmount(transactionRequest.getDepositAmount())
                    .withdrawalAmount(transactionRequest.getWithdrawalAmount())
                    .totalBalance(transactionRequest.getTotalBalance())
                    .createdAt(transactionRequest.getCreatedAt())
                    .build();
            transactionRepository.save(transactions);
            log.info("Transaction {} is saved", transactions.getId());


           return mapToTransactionResponse(transactions);

    }

    @Override
    @Transactional
    public List<Transactions> getTransactionsByAccountNumber(String accountNumber) throws InterruptedException {

        return transactionRepository.getTransactionsByAccountNumber(accountNumber);
    }


    @Override
    public List<Transactions> getAllTransactions() {

        return transactionRepository.findAll();

    }

    @Override
    public List<Transactions> findByOrderByCreatedAtDesc() {

        return transactionRepository.findByOrderByCreatedAtDesc();
    }


    private TransactionResponse mapToTransactionResponse(Transactions transactions) {

        return TransactionResponse.builder()
                .id(transactions.getId())
                .name(transactions.getName())
                .phone(transactions.getPhone())
                .accountNumber(transactions.getAccountNumber())
                .totalBalance(transactions.getTotalBalance())
                .depositAmount(transactions.getDepositAmount())
                .withdrawalAmount(transactions.getWithdrawalAmount())
                .createdAt(transactions.getCreatedAt())
                .build();
    }




}
