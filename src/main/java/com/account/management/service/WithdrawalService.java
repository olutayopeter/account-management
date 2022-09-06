package com.account.management.service;


import com.account.management.dto.WithdrawalDetailRequest;
import com.account.management.dto.WithdrawalDetailsResponse;
import com.account.management.dto.WithdrawalResponse;


public interface WithdrawalService {

    WithdrawalResponse withdrawal(WithdrawalDetailRequest withdrawalDetailsRequest) throws InterruptedException;
}
