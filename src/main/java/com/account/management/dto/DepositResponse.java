package com.account.management.dto;


import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class DepositResponse {

    private BigDecimal depositAmount;

    private BigDecimal totalBalance;
}
