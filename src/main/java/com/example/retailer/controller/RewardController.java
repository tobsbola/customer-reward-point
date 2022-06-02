package com.example.retailer.controller;

import com.example.retailer.model.RewardPoint;
import com.example.retailer.model.dto.CustomerDTO;
import com.example.retailer.model.dto.TransactionDTO;
import com.example.retailer.service.CustomerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class RewardController {
    private final CustomerService customerService;
    private static Logger log = LoggerFactory.getLogger(RewardController.class);

    public RewardController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/rewards")
    public ResponseEntity<List<RewardPoint>> getAllMonthlyRewards() {
        log.info("get all rewards");
        List<RewardPoint> rewardPointList = new ArrayList<>();
        List<TransactionDTO> transactionDTOList = customerService.getThreeMonthRecord();

        Map<CustomerDTO, List<TransactionDTO>> customerTnx = new HashMap<>();
        for (TransactionDTO transactionDTO: transactionDTOList) {
            if (!(customerTnx.containsKey(transactionDTO.getCustomer()))) {
                customerTnx.put(transactionDTO.getCustomer(), new ArrayList<TransactionDTO>());
            }
            customerTnx.get(transactionDTO.getCustomer()).add(transactionDTO);
        }

        customerTnx.forEach((cust, tnxs) -> {
            RewardPoint point = new RewardPoint(cust);
            for (TransactionDTO tnx: tnxs) {
                if (!(point.getAllTnx().containsKey(tnx.getTransactionDate().getMonth()))) {
                    point.getAllTnx().put(tnx.getTransactionDate().getMonth(), new ArrayList<TransactionDTO>());
                }
                point.getAllTnx().get(tnx.getTransactionDate().getMonth()).add(tnx);
            }
            rewardPointList.add(point);
        });

        return new ResponseEntity<>(rewardPointList, HttpStatus.OK);
    }

    @GetMapping("/rewards/customer/{id}")
    public ResponseEntity<RewardPoint> getSingleCustomerReward(@PathVariable Long id) {
        CustomerDTO customerDTO = customerService.findById(id);
        RewardPoint rewardPoint = new RewardPoint(customerDTO);

        List<TransactionDTO> transactionDTOList = customerService.findThreeMonthTnxByCustomerId(id);
        if (transactionDTOList == null || transactionDTOList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        for (TransactionDTO transactionDTO: transactionDTOList) {
            if (!rewardPoint.getAllTnx().containsKey(transactionDTO.getTransactionDate().getMonth())) {
                rewardPoint.getAllTnx().put(transactionDTO.getTransactionDate().getMonth(), new ArrayList<>());
            }
            rewardPoint.getAllTnx().get(transactionDTO.getTransactionDate().getMonth()).add(transactionDTO);
        }

        return new ResponseEntity<>(rewardPoint, HttpStatus.OK);
    }
}
