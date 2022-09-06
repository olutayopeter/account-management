package com.account.management.service;


import com.account.management.dto.AccountRequest;
import com.account.management.dto.AccountDetailResponse;
import com.account.management.dto.AccountResponse;
import com.account.management.model.Account;

import java.util.List;


public interface AccountService {

    AccountResponse createAccount(AccountRequest accountRequest) throws InterruptedException;

    AccountDetailResponse getAccountByAccountNumber(String accountNumber) throws InterruptedException;

    List<Account> getAll();

    void updateAccount(long id,Account account);

}
