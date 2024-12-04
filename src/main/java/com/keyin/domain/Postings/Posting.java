package com.keyin.domain.Postings;

import com.keyin.domain.Product.Product;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class Posting {
    @Id
    @SequenceGenerator(name = "posting_sequence", sequenceName = "posting_sequence", allocationSize = 1, initialValue = 1)
    @GeneratedValue(generator = "posting_sequence")
    private Long postingId;
    private String sellerId; // Changed to String to match AWS user ID
    @ElementCollection
    private List<Long> productId;
    private int quantity;
    private double price;

    @ManyToMany
    private List<Product> products;

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

    public List<Long> getProductId() {
        return productId;
    }

    public void setProductId(List<Long> productId) {
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

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}