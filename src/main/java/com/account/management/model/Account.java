package com.account.management.model;


import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;


@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "t_account")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String phone;

    @Column(name = "account_number")
    private String accountNumber;

    @Column(name = "total_balance")
    private BigDecimal totalBalance;


    @Version
    private Long version;

}
