package com.account.management.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DepositDetailRequest {

    private DepositRequest depositRequest;


    @Data
    public static class  DepositRequest {

        @NotEmpty(message = "Account number should not be null or empty")
        private String accountNumber;

        @NotEmpty(message = "Deposit amount should not be null or empty")
        private BigDecimal amount;

    }
}
