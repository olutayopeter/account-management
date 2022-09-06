package com.account.management.dto;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AccountResponse {

    private String accountNumber;
}
