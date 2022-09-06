package com.account.management.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.math.BigDecimal;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "t_transactions")
public class Transactions {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String phone;

    @Column(name = "account_number")
    private String accountNumber;

    @Column(name = "total_balance")
    private BigDecimal totalBalance;

    @Column(name = "deposit_amount")
    private BigDecimal depositAmount;

    @Column(name = "withdrawal_amount")
    private BigDecimal withdrawalAmount;

    @Column(name = "created_at")
    private String createdAt;

    @Version
    private Long version;


}
