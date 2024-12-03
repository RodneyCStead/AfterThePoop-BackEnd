package com.keyin.domain.Product;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;

@Entity
public class Product {
    @Id
    @SequenceGenerator(name = "product_sequence", sequenceName = "product_sequence", allocationSize = 1, initialValue = 1)
    @GeneratedValue(generator = "product_sequence")
    private Long productId;
    private String productName;
    private String description;
    private double kPercent; // Potassium percent
    private double nPercent; // Nitrogen percent
    private double pPercent; // Phosphorus percent

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getKPercent() {
        return kPercent;
    }

    public void setKPercent(double kPercent) {
        this.kPercent = kPercent;
    }

    public double getNPercent() {
        return nPercent;
    }

    public void setNPercent(double nPercent) {
        this.nPercent = nPercent;
    }

    public double getPPercent() {
        return pPercent;
    }

    public void setPPercent(double pPercent) {
        this.pPercent = pPercent;
    }
}