package com.account.management.controller;


import com.account.management.dto.TransactionRequest;
import com.account.management.dto.TransactionResponse;
import com.account.management.model.Transactions;
import com.account.management.service.TransactionsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.Instant;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class TransactionController {

    private final TransactionsService transactionsService;

    @PostMapping("/transaction")
    @ResponseStatus(HttpStatus.CREATED)
    public TransactionResponse createTransaction(@Valid @RequestBody TransactionRequest transactionRequest) throws InterruptedException {
        transactionRequest.setCreatedAt(String.valueOf(Instant.now()));
        return transactionsService.createTransaction(transactionRequest);
    }

    @GetMapping({"/transaction/{accountNumber}", "/transaction"})
    public List<Transactions> getTransaction(@PathVariable(name = "accountNumber", required = false) String accountNumber) throws InterruptedException {
        if (StringUtils.isEmpty(accountNumber)) {

            return transactionsService.getAllTransactions();
        }
        return transactionsService.getTransactionsByAccountNumber(accountNumber);
    }

    @GetMapping("/transaction/sort")
    public List<Transactions> findByOrderByCreatedAtDesc() throws InterruptedException {

        return transactionsService.findByOrderByCreatedAtDesc();
    }



}
