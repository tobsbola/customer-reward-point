package com.example.retailer.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class TransactionDTO {
    private static final BigDecimal ONE_POINT = new BigDecimal("50");
    private static final BigDecimal TWO_POINT = new BigDecimal("100");

    private Long id;
    private BigDecimal amount;

    LocalDateTime transactionDate;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @JsonIgnore
    private CustomerDTO customer;

    public BigDecimal getPoint() {
        if (amount.compareTo(ONE_POINT) > 0) {
            BigDecimal twoPoints = amount.subtract(TWO_POINT).multiply(new BigDecimal("2"));
            return ONE_POINT.add(twoPoints);
        } else {
            if (amount.compareTo(ONE_POINT) > 0) {
                return amount.subtract(ONE_POINT).multiply(new BigDecimal("1"));
            }
            return new BigDecimal("0");
        }
    }

}
