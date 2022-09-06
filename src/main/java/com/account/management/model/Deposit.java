package com.account.management.model;


import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "t_deposit")
public class Deposit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "account_number")
    private String accountNumber;

    @Column(name = "deposit_amount")
    private BigDecimal amount;

    @Version
    private Long version;
}
