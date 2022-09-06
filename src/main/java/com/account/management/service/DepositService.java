package com.account.management.service;


import com.account.management.dto.DepositDetailRequest;
import com.account.management.dto.DepositDetailResponse;
import com.account.management.dto.DepositResponse;

import java.math.BigDecimal;

public interface DepositService {

    DepositResponse deposit(DepositDetailRequest depositDetailRequest) throws InterruptedException;
}
