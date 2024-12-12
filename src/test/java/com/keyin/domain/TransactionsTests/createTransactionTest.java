package com.keyin.domain.TransactionsTests;

import com.keyin.domain.Postings.Posting;
import com.keyin.domain.Postings.PostingRepository;
import com.keyin.domain.Transactions.Transaction;
import com.keyin.domain.Transactions.TransactionRepository;
import com.keyin.domain.Transactions.TransactionServices;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class createTransactionTest {

    @Mock
    private TransactionRepository transactionRepository;

    @Mock
    private PostingRepository postingRepository;

    @InjectMocks
    private TransactionServices transactionServices;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    // Test for successful transaction creation.
    @Test
    void testCreateTransaction_Success() {
        Long postingId = 1L;
        String sellerId = "seller123";
        String buyerId = "buyer123";
        int quantity = 5;

        Posting posting = new Posting();
        posting.setPostingId(postingId);
        posting.setQuantity(10);
        posting.setPrice(20.0);

        when(postingRepository.findById(postingId)).thenReturn(Optional.of(posting));
        when(transactionRepository.save(any(Transaction.class))).thenAnswer(i -> i.getArguments()[0]);

        Transaction transaction = transactionServices.createTransaction(postingId, sellerId, buyerId, quantity);

        assertNotNull(transaction);
        assertEquals(posting, transaction.getPosting());
        assertEquals(sellerId, transaction.getSellerId());
        assertEquals(buyerId, transaction.getBuyerId());
        assertEquals(quantity, transaction.getQuantityPurchased());
        assertEquals(LocalDate.now(), transaction.getTransactionDate());
        assertEquals(BigDecimal.valueOf(100.0).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue(), transaction.getTransactionAmount());

        verify(postingRepository, times(1)).save(posting);
        verify(transactionRepository, times(1)).save(transaction);
    }

    //   Test for not enough quantity available.
    @Test
    void testCreateTransaction_NotEnoughQuantity() {
        Long postingId = 1L;
        String sellerId = "seller123";
        String buyerId = "buyer123";
        int quantity = 15;

        Posting posting = new Posting();
        posting.setPostingId(postingId);
        posting.setQuantity(10);

        when(postingRepository.findById(postingId)).thenReturn(Optional.of(posting));

        Exception exception = assertThrows(RuntimeException.class, () -> {
            transactionServices.createTransaction(postingId, sellerId, buyerId, quantity);
        });

        assertEquals("Not enough quantity available", exception.getMessage());
        verify(postingRepository, never()).save(any(Posting.class));
        verify(transactionRepository, never()).save(any(Transaction.class));
    }

    // Test for posting not found.
    @Test
    void testCreateTransaction_PostingNotFound() {
        Long postingId = 1L;
        String sellerId = "seller123";
        String buyerId = "buyer123";
        int quantity = 5;

        when(postingRepository.findById(postingId)).thenReturn(Optional.empty());

        Exception exception = assertThrows(RuntimeException.class, () -> {
            transactionServices.createTransaction(postingId, sellerId, buyerId, quantity);
        });

        assertEquals("Posting not found", exception.getMessage());
        verify(postingRepository, never()).save(any(Posting.class));
        verify(transactionRepository, never()).save(any(Transaction.class));
    }

    // Test for null posting ID.
    @Test
    void testCreateTransaction_NullPostingId() {
        Long postingId = null;
        String sellerId = "seller123";
        String buyerId = "buyer123";
        int quantity = 5;

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            transactionServices.createTransaction(postingId, sellerId, buyerId, quantity);
        });

        assertEquals("Posting ID cannot be null", exception.getMessage());
        verify(postingRepository, never()).save(any(Posting.class));
        verify(transactionRepository, never()).save(any(Transaction.class));
    }

    // Test for transaction amount calculation.
    @Test
    void testCreateTransaction_TransactionAmountCalculation() {
        Long postingId = 1L;
        String sellerId = "seller123";
        String buyerId = "buyer123";
        int quantity = 5;

        Posting posting = new Posting();
        posting.setPostingId(postingId);
        posting.setQuantity(10);
        posting.setPrice(20.0);

        when(postingRepository.findById(postingId)).thenReturn(Optional.of(posting));
        when(transactionRepository.save(any(Transaction.class))).thenAnswer(i -> i.getArguments()[0]);

        Transaction transaction = transactionServices.createTransaction(postingId, sellerId, buyerId, quantity);

        assertNotNull(transaction);
        assertEquals(BigDecimal.valueOf(100.0).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue(), transaction.getTransactionAmount());
        verify(postingRepository, times(1)).save(posting);
        verify(transactionRepository, times(1)).save(transaction);
    }
}