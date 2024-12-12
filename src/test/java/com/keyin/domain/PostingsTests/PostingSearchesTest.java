package com.keyin.domain.PostingsTests;

import com.keyin.domain.Postings.Posting;
import com.keyin.domain.Postings.PostingRepository;
import com.keyin.domain.Postings.PostingServices;
import com.keyin.domain.Product.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class PostingSearchesTest {

    @Mock
    private PostingRepository postingRepository;

    @InjectMocks
    private PostingServices postingServices;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    // Test cases for searchByNPercent for when the posting is found
    @Test
    void testSearchByNPercent_Found() {
        double nPercent = 10.0;
        Posting posting = new Posting();
        posting.setProduct(new Product());
        posting.setQuantity(5);

        when(postingRepository.findByNPercentAndQuantityGreaterThan(nPercent, 0)).thenReturn(List.of(posting));

        Iterable<Posting> result = postingServices.searchByNPercent(nPercent);

        assertEquals(1, ((Collection<?>) result).size());
        verify(postingRepository, times(1)).findByNPercentAndQuantityGreaterThan(nPercent, 0);
    }

    // Test for searchByNPercent for when the posting is not found
    @Test
    void testSearchByNPercent_NotFound() {
        double nPercent = 10.0;

        when(postingRepository.findByNPercentAndQuantityGreaterThan(nPercent, 0)).thenReturn(Collections.emptyList());

        Iterable<Posting> result = postingServices.searchByNPercent(nPercent);

        assertEquals(0, ((Collection<?>) result).size());
        verify(postingRepository, times(1)).findByNPercentAndQuantityGreaterThan(nPercent, 0);
    }

    // Test for searchByNPercent for when the nPercent is null
    @Test
    void testSearchByNPercent_NullValue() {
        Double nPercent = null;

        assertThrows(IllegalArgumentException.class, () -> postingServices.searchByNPercent(nPercent));

        verify(postingRepository, never()).findByNPercentAndQuantityGreaterThan(anyDouble(), anyInt());
    }

    // Test for searchByKPercent for when the posting is found
    @Test
    void testSearchByKPercent_Found() {
        double kPercent = 5.0;
        Posting posting = new Posting();
        posting.setProduct(new Product());
        posting.setQuantity(5);

        when(postingRepository.findByKPercentAndQuantityGreaterThan(kPercent, 0)).thenReturn(List.of(posting));

        Iterable<Posting> result = postingServices.searchByKPercent(kPercent);

        assertEquals(1, ((Collection<?>) result).size());
        verify(postingRepository, times(1)).findByKPercentAndQuantityGreaterThan(kPercent, 0);
    }

    // Test for searchByKPercent for when the posting is not found
    @Test
    void testSearchByKPercent_NotFound() {
        double kPercent = 5.0;

        when(postingRepository.findByKPercentAndQuantityGreaterThan(kPercent, 0)).thenReturn(Collections.emptyList());

        Iterable<Posting> result = postingServices.searchByKPercent(kPercent);

        assertEquals(0, ((Collection<?>) result).size());
        verify(postingRepository, times(1)).findByKPercentAndQuantityGreaterThan(kPercent, 0);
    }

    // Test for searchByKPercent for when the kPercent is null
    @Test
    void testSearchByKPercent_NullValue() {
        Double kPercent = null;

        assertThrows(IllegalArgumentException.class, () -> postingServices.searchByKPercent(kPercent));

        verify(postingRepository, never()).findByKPercentAndQuantityGreaterThan(anyDouble(), anyInt());
    }

    // Test for searchByPPercent for when the posting is found
    @Test
    void testSearchByPPercent_Found() {
        double pPercent = 3.0;
        Posting posting = new Posting();
        posting.setProduct(new Product());
        posting.setQuantity(5);

        when(postingRepository.findByPPercentAndQuantityGreaterThan(pPercent, 0)).thenReturn(List.of(posting));

        Iterable<Posting> result = postingServices.searchByPPercent(pPercent);

        assertEquals(1, ((Collection<?>) result).size());
        verify(postingRepository, times(1)).findByPPercentAndQuantityGreaterThan(pPercent, 0);
    }

    // Test for searchByPPercent for when the posting is not found
    @Test
    void testSearchByPPercent_NotFound() {
        double pPercent = 3.0;

        when(postingRepository.findByPPercentAndQuantityGreaterThan(pPercent, 0)).thenReturn(Collections.emptyList());

        Iterable<Posting> result = postingServices.searchByPPercent(pPercent);

        assertEquals(0, ((Collection<?>) result).size());
        verify(postingRepository, times(1)).findByPPercentAndQuantityGreaterThan(pPercent, 0);
    }

    // Test for searchByPPercent for when the pPercent is null
    @Test
    void testSearchByPPercent_NullValue() {
        Double pPercent = null;

        assertThrows(IllegalArgumentException.class, () -> postingServices.searchByPPercent(pPercent));

        verify(postingRepository, never()).findByPPercentAndQuantityGreaterThan(anyDouble(), anyInt());
    }

    // Test for searchByPrice for when the posting is found
    @Test
    void testSearchByPrice_Found() {
        double price = 100.0;
        Posting posting = new Posting();
        posting.setProduct(new Product());
        posting.setQuantity(5);

        when(postingRepository.findByPriceAndQuantityGreaterThan(price, 0)).thenReturn(List.of(posting));

        Iterable<Posting> result = postingServices.searchByPrice(price);

        assertEquals(1, ((Collection<?>) result).size());
        verify(postingRepository, times(1)).findByPriceAndQuantityGreaterThan(price, 0);
    }

    // Test for searchByPrice for when the posting is not found
    @Test
    void testSearchByPrice_NotFound() {
        double price = 100.0;

        when(postingRepository.findByPriceAndQuantityGreaterThan(price, 0)).thenReturn(Collections.emptyList());

        Iterable<Posting> result = postingServices.searchByPrice(price);

        assertEquals(0, ((Collection<?>) result).size());
        verify(postingRepository, times(1)).findByPriceAndQuantityGreaterThan(price, 0);
    }

    // Test for searchByPrice for when the price is null
    @Test
    void testSearchByPrice_NullValue() {
        Double price = null;

        assertThrows(IllegalArgumentException.class, () -> postingServices.searchByPrice(price));

        verify(postingRepository, never()).findByPriceAndQuantityGreaterThan(anyDouble(), anyInt());
    }
}