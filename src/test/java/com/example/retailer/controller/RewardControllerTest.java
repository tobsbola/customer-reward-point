package com.example.retailer.controller;

import com.example.retailer.service.CustomerService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest
class RewardControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CustomerService customerService;


    @Test
    public void shouldReturnDefaultMessage() throws Exception {
        this.mockMvc.perform(get("/api")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("Welcome to point reward service")));
    }

    @Test
    void getAllMonthlyRewards() throws Exception {
        this.mockMvc.perform(get("/api/rewards"))
                .andExpect(status().isOk());
    }

    @Test
    void getSingleCustomerReward() throws Exception {
        /*this.mockMvc.perform(get("/api/rewards/customer/1"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.customer").exists());*/
    }
}