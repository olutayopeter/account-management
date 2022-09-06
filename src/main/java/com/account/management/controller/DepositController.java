package com.account.management.controller;


import com.account.management.dto.DepositDetailRequest;
import com.account.management.dto.DepositDetailResponse;
import com.account.management.dto.DepositResponse;
import com.account.management.service.DepositService;
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
public class DepositController {

    private final DepositService depositService;

    @PostMapping("/deposit")
    public ResponseEntity<DepositResponse> deposit(@Valid @RequestBody DepositDetailRequest depositDetailRequest) throws InterruptedException {
        return new ResponseEntity<>(depositService.deposit(depositDetailRequest), HttpStatus.OK);
    }
}
