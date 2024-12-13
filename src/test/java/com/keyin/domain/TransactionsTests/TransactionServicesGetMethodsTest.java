package com.keyin.domain.TransactionsTests;

import com.keyin.domain.Transactions.Transaction;
import com.keyin.domain.Transactions.TransactionRepository;
import com.keyin.domain.Transactions.TransactionServices;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class TransactionServicesGetMethodsTest {

    @Mock
    private TransactionRepository transactionRepository;

    @InjectMocks
    private TransactionServices transactionServices;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    // Test the getTransactions method
    @Test
    void testGetTransactions() {
        Transaction transaction = new Transaction();
        Iterable<Transaction> transactions = Collections.singletonList(transaction);

        when(transactionRepository.findAll()).thenReturn(transactions);

        Iterable<Transaction> result = transactionServices.getTransactions();

        assertEquals(transactions, result);
        verify(transactionRepository, times(1)).findAll();
    }


    // Test the getTransactionById method by passing a valid transaction ID
    @Test
    void testGetTransactionById_Success() {
        Long transactionId = 1L;
        Transaction transaction = new Transaction();
        transaction.setTransactionId(transactionId);

        when(transactionRepository.findById(transactionId)).thenReturn(Optional.of(transaction));

        Transaction result = transactionServices.getTransactionById(transactionId);

        assertEquals(transaction, result);
        verify(transactionRepository, times(1)).findById(transactionId);
    }

    // Test the getTransactionById method by passing in a invalid transactionId
    @Test
    void testGetTransactionById_NotFound() {
        Long transactionId = 1L;

        when(transactionRepository.findById(transactionId)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> transactionServices.getTransactionById(transactionId));
        verify(transactionRepository, times(1)).findById(transactionId);
    }
}
