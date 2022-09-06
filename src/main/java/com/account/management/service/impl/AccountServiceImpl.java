package com.account.management.service.impl;

import com.account.management.Repository.AccountRepository;
import com.account.management.dto.AccountRequest;
import com.account.management.dto.AccountDetailResponse;
import com.account.management.dto.AccountResponse;
import com.account.management.model.Account;
import com.account.management.service.AccountService;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;


@RequiredArgsConstructor
@Service
@Slf4j
@Builder
public class AccountServiceImpl implements AccountService {


    private final AccountRepository accountRepository;

    @Override
    @Transactional
    public AccountResponse createAccount(AccountRequest accountRequest) throws InterruptedException {

        log.info("Calling create account service...");

        try {

            Account account = Account.builder()
                    .accountNumber(accountRequest.getAccountCreationRequest().getAccountNumber())
                    .name(accountRequest.getAccountCreationRequest().getName())
                    .phone(accountRequest.getAccountCreationRequest().getPhone())
                    .totalBalance(accountRequest.getAccountCreationRequest().getTotalBalance())
                    .build();
            accountRepository.save(account);
            Thread.sleep(1000);

            log.info("Account {} is saved", account.getId());

            mapToAccountDetailsResponse(account);

        } catch(Exception ex) {

            log.info("Account error: " + ex.fillInStackTrace());
        }

        return mapToAccountResponse(accountRequest.getAccountCreationRequest().getAccountNumber());

    }

    @Override
    @Transactional
    public AccountDetailResponse getAccountByAccountNumber(String accountNumber) throws InterruptedException {

        Account account = accountRepository.getAccountByAccountNumber(accountNumber);
        Thread.sleep(1000);
        return mapToAccountDetailsResponse(account);
    }

    private AccountDetailResponse mapToAccountDetailsResponse(Account account) {

        return AccountDetailResponse.builder()
                .id(account.getId())
                .name(account.getName())
                .phone(account.getPhone())
                .accountNumber(account.getAccountNumber())
                .totalBalance(account.getTotalBalance())
                .build();
    }

    private AccountResponse mapToAccountResponse(String accountNumber) {

        return AccountResponse.builder()
                .accountNumber(accountNumber)
                .build();
    }

    public List<Account> getAll() {

        return accountRepository.findAll();
    }

    @Override
    public void updateAccount(long id, Account account) {
        Account accountFromDb = (Account)this.accountRepository.findById(id).get();
        accountFromDb.setAccountNumber(account.getAccountNumber());
        accountFromDb.setName(account.getName());
        accountFromDb.setPhone(accountFromDb.getPhone());
        accountFromDb.setTotalBalance(account.getTotalBalance());
        this.accountRepository.save(accountFromDb);
        log.info("total balance updated successfully");
    }


}
