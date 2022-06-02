package com.example.retailer.repository;

import java.util.List;
import java.util.Optional;

import com.example.retailer.model.entity.Customer;
import com.example.retailer.model.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    @Query("SELECT t FROM Transaction t WHERE t.transactionDate >= DATEADD(MONTH, -3, CURRENT_DATE)")
    List<Transaction> findAllInPastThreeMonths();

    @Query("SELECT t FROM Transaction t WHERE t.customer = :customer AND t.transactionDate >= DATEADD(MONTH, -3, CURRENT_DATE)")
    List<Transaction> findAllInPastThreeMonthsByCustomer(@Param("customer") Optional<Customer> customer);
}
