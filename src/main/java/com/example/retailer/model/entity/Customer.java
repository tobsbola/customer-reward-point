package com.example.retailer.model.entity;

import lombok.*;

import javax.persistence.*;
import java.util.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
public class Customer {
    @Id
    @GeneratedValue
    private Long id;
    private String firstName;
    private String lastName;

    @OneToMany(mappedBy= "customer", targetEntity = Transaction.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Transaction> transaction;

    public Customer(Long id, String firstName, String lastName) {
        super();
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }
}
