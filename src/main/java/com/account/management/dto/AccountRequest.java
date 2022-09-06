package com.account.management.dto;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import java.math.BigDecimal;
import javax.validation.constraints.NotEmpty;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AccountRequest {

    private AccountCreationRequest  accountCreationRequest;

    @Data
    public static class AccountCreationRequest {

        @NotEmpty(message = "Name should not be null or empty")
        private String name;

        @NotEmpty(message = "Phone should not be null or empty")
        private String phone;

        @JsonIgnoreProperties(ignoreUnknown = true)
        private String accountNumber;

        @JsonIgnoreProperties(ignoreUnknown = true)
        private BigDecimal totalBalance;

    }


}
