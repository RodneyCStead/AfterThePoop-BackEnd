package com.keyin.domain.PostingsTests;

import com.keyin.domain.Postings.Posting;
import com.keyin.domain.Postings.PostingRepository;
import com.keyin.domain.Postings.PostingServices;
import com.keyin.domain.Product.Product;
import com.keyin.domain.Product.ProductRepository;
import com.keyin.domain.Transactions.TransactionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class createPostingTest {

    @Mock
    private PostingRepository postingRepository;

    @Mock
    private ProductRepository productRepository;

    @Mock
    private TransactionRepository transactionRepository;

    @Mock
    private JdbcTemplate jdbcTemplate;

    @InjectMocks
    private PostingServices postingServices;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    // Test for successful posting creation.
    @Test
    void testCreatePosting_NewProduct() {

        Product product = new Product();
        product.setProductName("Test Product");

        Posting posting = new Posting();
        posting.setProduct(product);
        posting.setQuantity(10);

        when(productRepository.findByProductName(anyString())).thenReturn(null);
        when(productRepository.save(any(Product.class))).thenReturn(product);
        when(postingRepository.save(any(Posting.class))).thenReturn(posting);

        Posting result = postingServices.createPosting(posting, "seller123");

        assertEquals(posting, result);
        verify(productRepository, times(1)).save(product);
        verify(postingRepository, times(1)).save(posting);
    }

    // Test for successful posting creation with existing product. by increasing the quantity of the existing posting.
    @Test
    void testCreatePosting_ExistingProduct() {
        Product product = new Product();
        product.setProductName("Test Product");

        Posting existingPosting = new Posting();
        existingPosting.setProduct(product);
        existingPosting.setQuantity(5);

        Posting newPosting = new Posting();
        newPosting.setProduct(product);
        newPosting.setQuantity(10);

        when(productRepository.findByProductName(anyString())).thenReturn(product);
        when(postingRepository.findByNutrients(anyDouble(), anyDouble(), anyDouble())).thenReturn(Optional.of(existingPosting));
        when(postingRepository.save(any(Posting.class))).thenReturn(existingPosting);

        Posting result = postingServices.createPosting(newPosting, "seller123");

        assertEquals(15, result.getQuantity());
        verify(productRepository, never()).save(product);
        verify(postingRepository, times(1)).save(existingPosting);
    }

    // Test creating a new posting for an existing product when no matching posting is found.
    @Test
    void testCreatePosting_ExistingProduct_NewPosting() {
        Product product = new Product();
        product.setProductName("Test Product");

        Posting existingPosting = new Posting();
        existingPosting.setProduct(product);
        existingPosting.setQuantity(5);

        Posting newPosting = new Posting();
        newPosting.setProduct(product);
        newPosting.setQuantity(10);

        when(productRepository.findByProductName(anyString())).thenReturn(product);
        when(postingRepository.findByNutrients(anyDouble(), anyDouble(), anyDouble())).thenReturn(Optional.empty());
        when(postingRepository.save(any(Posting.class))).thenReturn(newPosting);

        Posting result = postingServices.createPosting(newPosting, "seller123");

        assertEquals(10, result.getQuantity());
        verify(productRepository, never()).save(product);
        verify(postingRepository, times(1)).save(newPosting);
    }
}
