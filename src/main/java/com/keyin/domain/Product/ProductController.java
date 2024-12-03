package com.keyin.domain.Product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/post-product")
public class ProductController {

    @Autowired
    private ProductServices productServices;

    @PostMapping
    public Iterable<Product> createProducts(@RequestBody List<Product> products) {
        return productServices.createProducts(products);
    }
}