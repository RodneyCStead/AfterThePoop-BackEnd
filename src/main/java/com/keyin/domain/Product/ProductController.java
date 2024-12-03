package com.keyin.domain.Product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/post-product")
public class ProductController {

    @Autowired
    private ProductServices productServices;

    @PostMapping
    public Product createProduct(@RequestBody Product newProduct) {
        return productServices.createProduct(newProduct);
    }
}