package com.keyin.domain.Product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServices {

    @Autowired
    private ProductRepository productRepository;

    public Iterable<Product> createProducts(List<Product> products) {
        return productRepository.saveAll(products);
    }
}
