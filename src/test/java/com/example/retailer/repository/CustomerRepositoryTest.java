package com.example.retailer.repository;

import com.example.retailer.model.entity.Customer;
import com.example.retailer.utility.DataBuilder;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@DataJpaTest
class CustomerRepositoryTest {
    @Autowired
    private CustomerRepository repository;

    private final DataBuilder dataBuilder = new DataBuilder();

    @Test
    void testCustomerSave() {
        repository.save(dataBuilder.buildCustomerData("Tobs", "Bola"));
        repository.save(dataBuilder.buildCustomerData("Segun", "Adelade"));
        repository.save(dataBuilder.buildCustomerData("Felix", "Adejumo"));
        repository.save(dataBuilder.buildCustomerData("Toluwa", "Jiji"));

        List<Customer> customerList = repository.findAll();
        assertEquals(4, customerList.size());
    }
}