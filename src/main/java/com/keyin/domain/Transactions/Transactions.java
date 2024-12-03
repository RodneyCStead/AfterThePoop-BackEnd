package com.keyin.domain.Transactions;

import jakarta.persistence.*;

@Entity
public class Transactions {
    @Id
    @SequenceGenerator(name = "transactions_sequence", sequenceName = "transactions_sequence", allocationSize = 1, initialValue = 1)
    @GeneratedValue(generator = "transactions_sequence")
    private Long transId;
    private Long buyerId;
    private Long sellerId;
    private Long postingId;
    private int quantity;
    private double price;


    public Long getTransId() {
        return transId;
    }

    public void setTransId(Long transId) {
        this.transId = transId;
    }

    public Long getBuyerId() {
        return buyerId;
    }

    public void setBuyerId(Long buyerId) {
        this.buyerId = buyerId;
    }

    public Long getSellerId() {
        return sellerId;
    }

    public void setSellerId(Long sellerId) {
        this.sellerId = sellerId;
    }

    public Long getPostingId() {
        return postingId;
    }

    public void setPostingId(Long postingId) {
        this.postingId = postingId;
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
}
