package com.keyin.domain.Product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/post-product")
@CrossOrigin(origins = "http://localhost:3000")
public class ProductController {

    @Autowired
    private ProductServices productServices;

    @PostMapping
    public Iterable<Product> createProducts(@RequestBody List<Product> products) {
        return productServices.createProducts(products);
    }
}