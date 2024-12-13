package com.keyin.domain.ProductTests;

import com.keyin.domain.Product.Product;
import com.keyin.domain.Product.ProductRepository;
import com.keyin.domain.Product.ProductServices;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class ProductServicesGetMethodsTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductServices productServices;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    // Test the getProducts method
    @Test
    void testGetProducts() {
        Iterable<Product> products = Collections.singletonList(new Product());

        when(productRepository.findAll()).thenReturn(products);

        Iterable<Product> result = productServices.getProducts();

        assertEquals(products, result);
        verify(productRepository, times(1)).findAll();
    }
}
