package com.account.management.controller;


import com.account.management.dto.WithdrawalDetailRequest;
import com.account.management.dto.WithdrawalDetailsResponse;
import com.account.management.dto.WithdrawalResponse;
import com.account.management.service.WithdrawalService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class WithdrawalController {

    private final WithdrawalService withdrawalService;

    @PostMapping("/withdrawal")
    public ResponseEntity<WithdrawalResponse> deposit(@Valid @RequestBody WithdrawalDetailRequest withdrawalDetailRequest) throws InterruptedException {
        return new ResponseEntity<>(withdrawalService.withdrawal(withdrawalDetailRequest), HttpStatus.OK);
    }
}
