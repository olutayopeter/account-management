package com.account.management.controller;


import com.account.management.dto.AccountRequest;
import com.account.management.dto.AccountDetailResponse;
import com.account.management.dto.AccountResponse;
import com.account.management.model.Account;
import com.account.management.service.AccountService;
import com.account.management.utils.AccountNumberGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;

    @PostMapping("/account")
    public ResponseEntity<AccountResponse> createAccount(@Valid @RequestBody AccountRequest accountRequest) throws InterruptedException {
        accountRequest.getAccountCreationRequest().setTotalBalance(new BigDecimal(0.00));
        accountRequest.getAccountCreationRequest().setAccountNumber(AccountNumberGenerator.generate());
        return new ResponseEntity<>(accountService.createAccount(accountRequest), HttpStatus.CREATED);
    }

    @GetMapping("/account")
    public List<Account> findAll() {

        return accountService.getAll();

    }


}
