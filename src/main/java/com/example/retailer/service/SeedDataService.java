package com.example.retailer.service;

import com.example.retailer.model.entity.Customer;
import com.example.retailer.repository.CustomerRepository;
import com.example.retailer.repository.TransactionRepository;
import com.example.retailer.utility.DataBuilder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SeedDataService {
    private final CustomerRepository customerRepository;
    private final TransactionRepository transactionRepository;

    private final DataBuilder dataBuilder = new DataBuilder();

    public SeedDataService(CustomerRepository customerRepository, TransactionRepository transactionRepository) {
        this.customerRepository = customerRepository;
        this.transactionRepository = transactionRepository;
    }

    public void loadDataIntoDB() {
        List<Customer> custExist = customerRepository.findAll();
        if (custExist.isEmpty()) {
            Customer cust1 = customerRepository.save(dataBuilder.buildCustomerData("Tobi", "Bola"));
            Customer cust2 = customerRepository.save(dataBuilder.buildCustomerData("Ben", "Carson"));
            Customer cust3= customerRepository.save(dataBuilder.buildCustomerData("Luther", "King"));
            Customer cust4 = customerRepository.save(dataBuilder.buildCustomerData("Phil", "Jones"));

            transactionRepository.save(dataBuilder.buildTnxData(cust1, "120", "Hand Bag", 2, 4));
            transactionRepository.save(dataBuilder.buildTnxData(cust1, "45", "Soap", 2, 5));
            transactionRepository.save(dataBuilder.buildTnxData(cust1, "350", "Ticket", 2, 9));
            transactionRepository.save(dataBuilder.buildTnxData(cust1, "40", "Water", 2, 1));

            transactionRepository.save(dataBuilder.buildTnxData(cust2, "150", "Sugar", 0, 2));
            transactionRepository.save(dataBuilder.buildTnxData(cust2, "12", "Knife", 0, 3));
            transactionRepository.save(dataBuilder.buildTnxData(cust2, "190", "Fish", 2, 5));
            transactionRepository.save(dataBuilder.buildTnxData(cust2, "44", "Orange", 1, 6));

            transactionRepository.save(dataBuilder.buildTnxData(cust3, "60", "Tomatoes", 1, 8));
            transactionRepository.save(dataBuilder.buildTnxData(cust3, "65", "Maggi", 1, 7));
            transactionRepository.save(dataBuilder.buildTnxData(cust3, "90", "Carrot", 2, 1));
            transactionRepository.save(dataBuilder.buildTnxData(cust3, "120", "Charger", 0, 8));

            transactionRepository.save(dataBuilder.buildTnxData(cust4, "12", "Car", 0, 2));
            transactionRepository.save(dataBuilder.buildTnxData(cust4, "5", "Beans", 1, 7));
            transactionRepository.save(dataBuilder.buildTnxData(cust4, "19", "Rice", 2, 19));
            transactionRepository.save(dataBuilder.buildTnxData(cust4, "4", "Coffee", 2, 12));
        }
    }

}
