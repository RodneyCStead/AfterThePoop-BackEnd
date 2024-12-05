package com.keyin.domain.Transactions;

import com.keyin.domain.Postings.Posting;
import jakarta.persistence.*;
import java.util.List;

@Entity
public class Transactions {
    @Id
    @SequenceGenerator(name = "transaction_sequence", sequenceName = "transaction_sequence", allocationSize = 1, initialValue = 1)
    @GeneratedValue(generator = "transaction_sequence")
    private Long transactionId;
    private String sellerId; // Changed to String to match AWS user ID
    private String buyerId; // Changed to String to match AWS user ID

    @ManyToOne
    private List<Posting> postings;

    public Long getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Long transactionId) {
        this.transactionId = transactionId;
    }

    public String getSellerId() {
        return sellerId;
    }

    public void setSellerId(String sellerId) {
        this.sellerId = sellerId;
    }

    public String getBuyerId() {
        return buyerId;
    }

    public void setBuyerId(String buyerId) {
        this.buyerId = buyerId;
    }

    public List<Posting> getPostings() {
        return postings;
    }

    public void setPostings(List<Posting> postings) {
        this.postings = postings;
    }
}