package com.example.retailer.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "Transactions")
@ToString
public class Transaction {
    @Id
    @GeneratedValue
    private Long id;

    private BigDecimal amount;
    private String productName;

//    @JsonIgnore
    @JoinColumn(name = "customer_id")
    @ManyToOne
    private Customer customer;

    LocalDateTime transactionDate;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;
}
