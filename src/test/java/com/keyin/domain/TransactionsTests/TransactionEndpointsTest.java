package com.keyin.domain.TransactionsTests;

import com.keyin.domain.Transactions.Transaction;
import com.keyin.domain.Transactions.TransactionController;
import com.keyin.domain.Transactions.TransactionServices;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import java.util.Collections;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(TransactionController.class)
class TransactionEndpointsTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TransactionServices transactionServices;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateTransaction() throws Exception {
        Transaction transaction = new Transaction();
        transaction.setTransactionId(1L);
        when(transactionServices.createTransaction(anyLong(), anyString(), anyString(), anyInt())).thenReturn(transaction);

        mockMvc.perform(post("/buy")
                        .param("postingId", "1")
                        .param("sellerId", "testSeller")
                        .param("buyerId", "testBuyer")
                        .param("quantity", "10"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.transactionId").value(1));

        verify(transactionServices, times(1)).createTransaction(1L, "testSeller", "testBuyer", 10);
    }

    @Test
    void testGetAllTransactions() throws Exception {
        when(transactionServices.getTransactions()).thenReturn(Collections.emptyList());

        mockMvc.perform(get("/buy"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray());

        verify(transactionServices, times(1)).getTransactions();
    }

    @Test
    void testGetTransactionById() throws Exception {
        Transaction transaction = new Transaction();
        transaction.setTransactionId(1L);
        when(transactionServices.getTransactionById(anyLong())).thenReturn(transaction);

        mockMvc.perform(get("/buy/transaction")
                        .param("transactionId", "1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.transactionId").value(1));

        verify(transactionServices, times(1)).getTransactionById(1L);
    }
}