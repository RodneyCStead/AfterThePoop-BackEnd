package com.keyin.domain.ProductTests;

import com.keyin.domain.Product.Product;
import com.keyin.domain.Product.ProductController;
import com.keyin.domain.Product.ProductServices;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import java.util.Collections;
import java.util.List;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ProductController.class)
class ProductEndpointsTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductServices productServices;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateProducts() throws Exception {
        Product product = new Product();
        product.setProductId(1L);
        product.setProductName("Test Product");
        List<Product> products = Collections.singletonList(product);
        when(productServices.createProducts(anyList())).thenReturn(products);

        mockMvc.perform(post("/post-product")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("[{\"productId\":1,\"productName\":\"Test Product\"}]"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].productId").value(1))
                .andExpect(jsonPath("$[0].productName").value("Test Product"));

        verify(productServices, times(1)).createProducts(anyList());
    }

    @Test
    void testGetProducts() throws Exception {
        Product product = new Product();
        product.setProductId(1L);
        product.setProductName("Test Product");
        List<Product> products = Collections.singletonList(product);
        when(productServices.getProducts()).thenReturn(products);

        mockMvc.perform(get("/post-product"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].productId").value(1))
                .andExpect(jsonPath("$[0].productName").value("Test Product"));

        verify(productServices, times(1)).getProducts();
    }
}