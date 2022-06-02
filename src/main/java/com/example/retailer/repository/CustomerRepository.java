package com.example.retailer.repository;

import com.example.retailer.model.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

@Repository
@Transactional
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    @Query(value = "SELECT id FROM Customer LIMIT 1", nativeQuery = true)
    Optional<Customer> findOne();

    Customer findByFirstName(String wes);
}
