package com.example.retailer.model.dto;

import com.example.retailer.utility.DataBuilder;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@DataJpaTest
class TransactionDTOTest {

    private final DataBuilder dataBuilder = new DataBuilder();

    @Test
    void testRewardAbove100() {

    }

}