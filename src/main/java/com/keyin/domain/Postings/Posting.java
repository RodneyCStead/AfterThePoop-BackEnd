package com.keyin.domain.Postings;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.keyin.domain.Product.Product;
import com.keyin.domain.Transactions.Transaction;
import jakarta.persistence.*;

import java.util.Set;

@Entity
public class Posting {
    @Id
    @SequenceGenerator(name = "posting_sequence", sequenceName = "posting_sequence", allocationSize = 1, initialValue = 1)
    @GeneratedValue(generator = "posting_sequence")
    private Long postingId;
    private String sellerId; // Changed to String to match AWS user ID
    private Long productId;
    private int quantity;
    private double price;

    @ManyToOne
    private Product product;

    @OneToMany(mappedBy = "posting", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private Set<Transaction> transactions;

    public Long getPostingId() {
        return postingId;
    }

    public void setPostingId(Long postingId) {
        this.postingId = postingId;
    }

    public String getSellerId() {
        return sellerId;
    }

    public void setSellerId(String sellerId) {
        this.sellerId = sellerId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Set<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(Set<Transaction> transactions) {
        this.transactions = transactions;
    }
}