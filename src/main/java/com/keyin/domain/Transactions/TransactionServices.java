package com.keyin.domain.Transactions;

import com.keyin.domain.Postings.Posting;
import com.keyin.domain.Postings.PostingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
public class TransactionServices {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private PostingRepository postingRepository;

    @Transactional
    public Transaction createTransaction(Long postingId, String sellerId, String buyerId, int quantity) {
        Posting posting = postingRepository.findById(postingId)
                .orElseThrow(() -> new RuntimeException("Posting not found"));

        if (posting.getQuantity() < quantity) {
            throw new RuntimeException("Not enough quantity available");
        }

        posting.setQuantity(posting.getQuantity() - quantity);
        postingRepository.save(posting);

        Transaction transaction = new Transaction();
        transaction.setPosting(posting);
        transaction.setSellerId(sellerId);
        transaction.setBuyerId(buyerId);
        transaction.setQuantityPurchased(quantity);
        transaction.setTransactionDate(LocalDate.now());

        return transactionRepository.save(transaction);
    }
}