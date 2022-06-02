package com.example.retailer.utility;

import com.example.retailer.model.dto.CustomerDTO;
import com.example.retailer.model.dto.TransactionDTO;
import com.example.retailer.model.entity.Customer;
import com.example.retailer.model.entity.Transaction;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;

public class DataBuilder {
    public Customer buildCustomerData(String firstName, String lastName) {
        Customer customer = new Customer();
        customer.setFirstName(firstName);
        customer.setLastName(lastName);
        return customer;
    }

    public Transaction buildTnxData(Customer customer, String amount, String productName, int month, int days) {
        /*LocalDateTime lastCoupleOfMonth = LocalDateTime.now().minusMonths(month).minusDays(days);
        return new Transaction(id, new BigDecimal(amount), productName, customer, lastCoupleOfMonth, new Date());*/
        Transaction tnx = new Transaction();
        tnx.setProductName(productName);
        tnx.setCustomer(customer);
        tnx.setAmount(new BigDecimal(amount));
        tnx.setCreatedAt(new Date());
        LocalDateTime pastMonth = LocalDateTime.now().minusMonths(month).minusDays(days);
        tnx.setTransactionDate(pastMonth);
        return tnx;
    }

    public TransactionDTO buildTnxDtoData(CustomerDTO customer, String amount, String productName, int month, int days) {
        /*LocalDateTime lastCoupleOfMonth = LocalDateTime.now().minusMonths(month).minusDays(days);
        return new Transaction(id, new BigDecimal(amount), productName, customer, lastCoupleOfMonth, new Date());*/
        TransactionDTO tnx = new TransactionDTO();
//        tnx.setProductName(productName);
        tnx.setCustomer(customer);
        tnx.setAmount(new BigDecimal(amount));
        tnx.setCreatedAt(new Date());
        LocalDateTime pastMonth = LocalDateTime.now().minusMonths(month).minusDays(days);
        tnx.setTransactionDate(pastMonth);
        return tnx;
    }
}
