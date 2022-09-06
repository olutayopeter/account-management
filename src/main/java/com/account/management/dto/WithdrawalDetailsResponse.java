package com.account.management.dto;


import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class WithdrawalDetailsResponse {

        private BigDecimal withdrawalAmount;

        private BigDecimal totalBalance;

        private String accountNumber;

        private String response;

}
