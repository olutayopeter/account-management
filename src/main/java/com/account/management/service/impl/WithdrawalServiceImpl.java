package com.account.management.service.impl;

import com.account.management.Repository.AccountRepository;
import com.account.management.Repository.WithdrawalRepository;
import com.account.management.client.TransactionClient;
import com.account.management.dto.*;
import com.account.management.model.Account;
import com.account.management.model.Withdrawal;
import com.account.management.service.AccountService;
import com.account.management.service.WithdrawalService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.time.Instant;

@Service
@Slf4j
@RequiredArgsConstructor
public class WithdrawalServiceImpl implements WithdrawalService {


    private final WithdrawalRepository withdrawalRepository;
    private final AccountService accountService;





    @Override
    @Transactional
    public WithdrawalResponse withdrawal(WithdrawalDetailRequest withdrawalDetailRequest) throws InterruptedException {

        try {

            log.info("Calling withdrawal service...");

            TransactionRequest transactionRequest = new TransactionRequest();

            TransactionClient transactionClient = new TransactionClient(new RestTemplate());

            AccountDetailResponse accountResponse = accountService.getAccountByAccountNumber(withdrawalDetailRequest.getWithdrawalRequest().getAccountNumber());

            if (accountResponse.getAccountNumber() != null || accountResponse.getAccountNumber().isEmpty()) {

                Withdrawal withdrawal = Withdrawal.builder().accountNumber(withdrawalDetailRequest.getWithdrawalRequest().getAccountNumber())
                        .withdrawalAmount(accountResponse.getTotalBalance().subtract(withdrawalDetailRequest.getWithdrawalRequest().getWithdrawalAmount()))
                        .build();
                withdrawalRepository.save(withdrawal);

                transactionRequest.setName(accountResponse.getName());
                transactionRequest.setPhone(accountResponse.getPhone());
                transactionRequest.setDepositAmount(null);
                transactionRequest.setWithdrawalAmount(withdrawalDetailRequest.getWithdrawalRequest().getWithdrawalAmount());
                transactionRequest.setAccountNumber(accountResponse.getAccountNumber());
                transactionRequest.setTotalBalance(accountResponse.getTotalBalance().subtract(withdrawalDetailRequest.getWithdrawalRequest().getWithdrawalAmount()));
                transactionRequest.setCreatedAt(String.valueOf(Instant.now()));
                transactionClient.addTransaction(transactionRequest);
                transactionClient.addTransaction(transactionRequest);
                Thread.sleep(1000);

                Account account = new Account();

                account.setId(accountResponse.getId());
                account.setAccountNumber(accountResponse.getAccountNumber());
                account.setPhone(accountResponse.getPhone());
                account.setName(accountResponse.getName());
                account.setTotalBalance(transactionRequest.getTotalBalance());
                accountService.updateAccount(account.getId(), account);


                mapToWithdrawalDetailResponse(withdrawalDetailRequest, accountResponse);

                return mapToWithdrawalResponse(withdrawalDetailRequest.getWithdrawalRequest().getWithdrawalAmount(), transactionRequest.getTotalBalance(), accountResponse);


            }

        } catch(Exception ex) {

            log.info("withdrwal error: " + ex.fillInStackTrace());
        }



        return null;
    }


    private WithdrawalDetailsResponse mapToWithdrawalDetailResponse(WithdrawalDetailRequest withdrawalDetailRequest, AccountDetailResponse accountResponse) {

        return WithdrawalDetailsResponse.builder()
                .accountNumber(withdrawalDetailRequest.getWithdrawalRequest().getAccountNumber())
                .withdrawalAmount(withdrawalDetailRequest.getWithdrawalRequest().getWithdrawalAmount())
                .totalBalance(accountResponse.getTotalBalance())
                .build();

    }

    private WithdrawalResponse mapToWithdrawalResponse(BigDecimal withdrawalAmount,BigDecimal totalBalance, AccountDetailResponse accountResponse) {

        return WithdrawalResponse.builder()
                .withdrawalAmount(withdrawalAmount)
                .totalBalance(totalBalance)
                .build();

    }

}
