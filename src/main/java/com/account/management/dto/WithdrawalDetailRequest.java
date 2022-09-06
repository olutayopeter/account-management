package com.account.management.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import java.math.BigDecimal;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class WithdrawalDetailRequest {

    private WithdrawalRequest withdrawalRequest;


    @Data
    public static class WithdrawalRequest {

        @NotEmpty(message = "Account number should not be null or empty")
        private String accountNumber;

        @NotEmpty(message = "Withdrawal amount should not be null or empty")
        private BigDecimal withdrawalAmount;

    }

}
