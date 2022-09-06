package com.account.management.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TransactionResponse {


        private Long id;

        private String name;

        private String phone;

        private String accountNumber;

        private BigDecimal totalBalance;

        private BigDecimal depositAmount;

        private BigDecimal withdrawalAmount;

        private String createdAt;


}
