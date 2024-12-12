package com.keyin.domain.ProductTests;

import com.keyin.domain.Product.Product;
import com.keyin.domain.Product.ProductRepository;
import com.keyin.domain.Product.ProductServices;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class createProductsTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductServices productServices;

    public createProductsTest() {
        MockitoAnnotations.openMocks(this);
    }

    // Test for successful product creation.
    @Test
    void testCreateProducts() {
        Product product1 = new Product();
        Product product2 = new Product();
        List<Product> products = Arrays.asList(product1, product2);

        when(productRepository.saveAll(products)).thenReturn(products);

        Iterable<Product> result = productServices.createProducts(products);

        assertEquals(products, result);
        verify(productRepository, times(1)).saveAll(products);
    }
}