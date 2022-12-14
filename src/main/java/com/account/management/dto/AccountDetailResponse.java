package com.account.management.dto;


import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class AccountDetailResponse {


    private Long id;

    private String name;

    private String phone;

    private String accountNumber;

    private BigDecimal totalBalance;


}
