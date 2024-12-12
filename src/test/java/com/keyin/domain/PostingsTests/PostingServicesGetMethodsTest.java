package com.keyin.domain.PostingsTest;


import com.keyin.domain.Postings.Posting;
import com.keyin.domain.Postings.PostingRepository;
import com.keyin.domain.Postings.PostingServices;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class PostingServicesGetMethodsTest {

    @Mock
    private PostingRepository postingRepository;

    @InjectMocks
    private PostingServices postingServices;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    // Test the getPostings method
    @Test
    void testGetPostings() {
        Posting posting = new Posting();
        Iterable<Posting> postings = Collections.singletonList(posting);

        when(postingRepository.findByQuantityGreaterThan(0)).thenReturn(postings);

        Iterable<Posting> result = postingServices.getPostings();

        assertEquals(postings, result);
        verify(postingRepository, times(1)).findByQuantityGreaterThan(0);
    }

    // Test the getPostingsBySellerId method
    @Test
    void testGetPostingsBySellerId() {
        String sellerId = "seller123";
        Posting posting = new Posting();
        Iterable<Posting> postings = Collections.singletonList(posting);

        when(postingRepository.findBySellerIdAndQuantityGreaterThan(sellerId, 0)).thenReturn(postings);

        Iterable<Posting> result = postingServices.getPostingsBySellerId(sellerId);

        assertEquals(postings, result);
        verify(postingRepository, times(1)).findBySellerIdAndQuantityGreaterThan(sellerId, 0);
    }
}