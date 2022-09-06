package com.account.management.dto;


import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class DepositDetailResponse {

        private BigDecimal depositAmount;

        private BigDecimal totalBalance;

        private String accountNumber;

}
