package com.account.management.model;


import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;


@Data
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "withdrawal")
public class Withdrawal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "account_number")
    private String accountNumber;

    @Column(name = "withdrawal_amount")
    private BigDecimal withdrawalAmount;

    @Version
    private Long version;
}
