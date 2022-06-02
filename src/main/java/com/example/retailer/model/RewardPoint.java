package com.example.retailer.model;

import com.example.retailer.model.dto.CustomerDTO;
import com.example.retailer.model.dto.TransactionDTO;

import java.math.BigDecimal;
import java.time.Month;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RewardPoint {
    private CustomerDTO customerDTO;
    private final Map<Month, List<TransactionDTO>> tnx = new HashMap<>();

    public RewardPoint() {
        super();
    }

    public RewardPoint(CustomerDTO customerDTO) {
        super();
        this.customerDTO = customerDTO;
    }

    public CustomerDTO getCustomer() {
        return customerDTO;
    }

    public Map<Month, List<TransactionDTO>> getAllTnx() {
        return tnx;
    }

    public Map<Month, BigDecimal> getTotalRewardPoint() {
        Map<Month, BigDecimal> rewardPoint = new HashMap<>();
        tnx.forEach((m, monthlyTnx) -> {
            BigDecimal amt = new BigDecimal("0");
            for (TransactionDTO tnxDto: monthlyTnx) {
                amt.add(tnxDto.getPoint());
            }
            rewardPoint.put(m, amt);
        });
        return rewardPoint;
    }
}
