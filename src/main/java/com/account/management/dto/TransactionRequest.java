package com.account.management.dto;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.math.BigDecimal;

@Data
public class TransactionRequest {

        private String name;

        private String phone;

        private String accountNumber;

        private BigDecimal totalBalance;

        private BigDecimal depositAmount;

        private BigDecimal withdrawalAmount;

        @JsonIgnoreProperties(ignoreUnknown = true)
        private String createdAt;

}
