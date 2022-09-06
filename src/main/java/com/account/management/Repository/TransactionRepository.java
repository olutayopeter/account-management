package com.account.management.Repository;


import com.account.management.model.Account;
import com.account.management.model.Transactions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transactions, Long> {

    @Query(value = "SELECT * from t_transactions t where t.account_number = :accountNumber", nativeQuery = true)
    List<Transactions> getTransactionsByAccountNumber(String accountNumber);

    @Query(value = "SELECT * from t_transactions t order by t.created_at DESC", nativeQuery = true)
    List<Transactions> findByOrderByCreatedAtDesc();

}
