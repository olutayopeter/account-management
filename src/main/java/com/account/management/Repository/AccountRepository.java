package com.account.management.Repository;


import com.account.management.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account,Long> {

    @Query(value = "SELECT * from t_account t where t.account_number = :accountNumber", nativeQuery = true)
    public Account getAccountByAccountNumber(String accountNumber);

    @Query(value = "UPDATE t_account t SET t.total_balance = :totalBalance where t.account_number = :accountNumber", nativeQuery = true)
    Integer updateTotalBalanceByAccountNumber(BigDecimal totalBalance, String accountNumber);

}
