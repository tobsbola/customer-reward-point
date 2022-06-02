package com.example.retailer.repository;

import com.example.retailer.model.entity.Transaction;
import com.example.retailer.utility.DataBuilder;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
class TransactionRepositoryTest {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private CustomerRepository customerRepository;
    private final DataBuilder dataBuilder = new DataBuilder();

    @Test
    void findAllInPastThreeMonths() {

    }

    @Test
    void testTransactionSave() {
        customerRepository.save(dataBuilder.buildCustomerData("Wes", "Bos"));
        transactionRepository.save(dataBuilder.buildTnxData(customerRepository.findByFirstName("Wes"),  "200", "versace", 1, 5));
        transactionRepository.save(dataBuilder.buildTnxData(customerRepository.findByFirstName("Wes"),  "45", "Bread", 0, 2));

        List<Transaction> transactionList = transactionRepository.findAll();
        assertEquals(2, transactionList.size());

        assertEquals(2, transactionRepository.findAllInPastThreeMonths().size());
    }
}