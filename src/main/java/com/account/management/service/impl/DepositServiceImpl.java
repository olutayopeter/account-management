package com.account.management.service.impl;


import com.account.management.Repository.AccountRepository;
import com.account.management.Repository.DepositRepository;
import com.account.management.client.TransactionClient;
import com.account.management.dto.*;
import com.account.management.model.Account;
import com.account.management.model.Deposit;
import com.account.management.service.AccountService;
import com.account.management.service.DepositService;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import java.math.BigDecimal;
import java.time.Instant;


@Slf4j
@Service
@Builder
@RequiredArgsConstructor
public class DepositServiceImpl implements DepositService {

    private final DepositRepository depositRepository;
    private final AccountService accountService;


    @Override
    @Transactional
    public DepositResponse deposit(DepositDetailRequest depositDetailRequest) throws InterruptedException {

        TransactionClient transactionClient = new TransactionClient(new RestTemplate());

        TransactionRequest transactionRequest = new TransactionRequest();

        Account account = new Account();

        log.info("Calling create deposit service...");

        try {

            AccountDetailResponse accountResponse = accountService.getAccountByAccountNumber(depositDetailRequest.getDepositRequest().getAccountNumber());
            log.info("Account details response: " + accountResponse);
            log.info("accountNumber: " + accountResponse.getAccountNumber());

            if (accountResponse.getAccountNumber() != null || accountResponse.getAccountNumber().isEmpty()) {

                Deposit deposit = Deposit.builder()
                        .accountNumber(depositDetailRequest.getDepositRequest().getAccountNumber())
                        .amount(depositDetailRequest.getDepositRequest().getAmount())
                        .build();
                depositRepository.save(deposit);

                log.info("Calling transaction service 2.. ");

                transactionRequest.setName(accountResponse.getName());
                transactionRequest.setPhone(accountResponse.getPhone());
                transactionRequest.setDepositAmount(depositDetailRequest.getDepositRequest().getAmount());
                transactionRequest.setWithdrawalAmount(null);
                transactionRequest.setAccountNumber(accountResponse.getAccountNumber());
                transactionRequest.setTotalBalance(depositDetailRequest.getDepositRequest().getAmount().add(accountResponse.getTotalBalance()));
                transactionRequest.setCreatedAt(String.valueOf(Instant.now()));
                String transactionResponse = transactionClient.addTransaction(transactionRequest);
                log.info("transactions details: " + transactionResponse);

                account.setId(accountResponse.getId());
                account.setAccountNumber(accountResponse.getAccountNumber());
                account.setPhone(accountResponse.getPhone());
                account.setName(accountResponse.getName());
                account.setTotalBalance(transactionRequest.getTotalBalance());
                accountService.updateAccount(account.getId(), account);

                Thread.sleep(1000);


                mapToDepositDetailResponse(depositDetailRequest, accountResponse);

                return mapToDepositResponse(depositDetailRequest.getDepositRequest().getAmount(), transactionRequest.getTotalBalance(), accountResponse);


            }

        } catch(Exception ex) {

            log.info("Deposit error: " + ex.fillInStackTrace());
        }


        return null;
    }


    private DepositDetailResponse mapToDepositDetailResponse(DepositDetailRequest depositDetailRequest,AccountDetailResponse accountDetailResponse) {

        return DepositDetailResponse.builder()
                .accountNumber(depositDetailRequest.getDepositRequest().getAccountNumber())
                .depositAmount(depositDetailRequest.getDepositRequest().getAmount())
                .totalBalance(accountDetailResponse.getTotalBalance())
                .build();

    }

    private DepositResponse mapToDepositResponse(BigDecimal amountDeposit, BigDecimal totalBalance, AccountDetailResponse accountDetailResponse) {

        return DepositResponse.builder()
                .depositAmount(amountDeposit)
                .totalBalance(totalBalance)
                .build();

    }


}
