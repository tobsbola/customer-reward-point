package com.example.retailer.service;

import com.example.retailer.model.dto.CustomerDTO;
import com.example.retailer.model.dto.TransactionDTO;
import com.example.retailer.model.entity.Customer;
import com.example.retailer.model.entity.Transaction;
import com.example.retailer.repository.CustomerRepository;
import com.example.retailer.repository.TransactionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CustomerService {
    private final Logger logger = LoggerFactory.getLogger(CustomerService.class);
    private final CustomerRepository customerRepository;
    private final TransactionRepository transactionRepository;
    private final SeedDataService mockDataService;

    public CustomerService(CustomerRepository customerRepository, TransactionRepository transactionRepository, SeedDataService mockDataService) {
        this.customerRepository = customerRepository;
        this.transactionRepository = transactionRepository;
        this.mockDataService = mockDataService;
    }

    private CustomerDTO parseCustomerDtoToEntity(Customer customer) {
        CustomerDTO cust = new CustomerDTO();
        BeanUtils.copyProperties(customer, cust);
        return cust;
    }

    public CustomerDTO findById(Long id) {
        CustomerDTO target = new CustomerDTO();
        Optional<Customer> cust = customerRepository.findById(id);
        if (cust.isPresent()) {
            BeanUtils.copyProperties(cust.get(), target);
            return target;
        }
        return null;
    }

    public List<TransactionDTO> getThreeMonthRecord() {
        mockDataService.loadDataIntoDB();
        List<TransactionDTO> lastThreeMonthTnx = new ArrayList<>();
        List<Transaction> tnxList = transactionRepository.findAllInPastThreeMonths();

        logger.info(Arrays.toString(tnxList.toArray()));

        tnxList.forEach(singleTnx -> {
            TransactionDTO tnxDTO = new TransactionDTO();
            BeanUtils.copyProperties(singleTnx, tnxDTO);
            CustomerDTO cDto = parseCustomerDtoToEntity(singleTnx.getCustomer());
            tnxDTO.setCustomer(cDto);

            lastThreeMonthTnx.add(tnxDTO);
        });

        return lastThreeMonthTnx;
    }

    public List<TransactionDTO> findThreeMonthTnxByCustomerId(Long id) {
        mockDataService.loadDataIntoDB();
        Optional<Customer> cust = customerRepository.findById(id);
        if (cust.isEmpty())
            return null;

        List<TransactionDTO> lastThreeMonthTnx = new ArrayList<>();
        List<Transaction> tnxList = transactionRepository.findAllInPastThreeMonthsByCustomer(cust);

        tnxList.forEach(singleTnx -> {
            TransactionDTO tnxDto = new TransactionDTO();
            BeanUtils.copyProperties(singleTnx, tnxDto);
            CustomerDTO customerDTO = parseCustomerDtoToEntity(singleTnx.getCustomer());
            tnxDto.setCustomer(customerDTO);
            lastThreeMonthTnx.add(tnxDto);
        });
        return lastThreeMonthTnx;
    }
}
