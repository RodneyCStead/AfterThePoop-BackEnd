package com.keyin.domain.PostingsTests;

import com.keyin.domain.Postings.Posting;
import com.keyin.domain.Postings.PostingRepository;
import com.keyin.domain.Postings.PostingServices;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class deletePostingTest {

    @Mock
    private PostingRepository postingRepository;

    @InjectMocks
    private PostingServices postingServices;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    // Test for successful posting deletion.
    @Test
    void testDeletePosting_ByPostingId() {
        Long postingId = 1L;
        Posting posting = new Posting();
        posting.setPostingId(postingId);
        posting.setQuantity(10);

        when(postingRepository.findById(postingId)).thenReturn(Optional.of(posting));

        postingServices.deletePosting(postingId);

        assertEquals(0, posting.getQuantity());
        verify(postingRepository, times(1)).save(posting);
    }

    // Test for posting deletion when posting ID is not found.
    @Test
    void testDeletePosting_ByPostingId_IdNotFound() {
        Long postingId = 1L;

        when(postingRepository.findById(postingId)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> postingServices.deletePosting(postingId));

        verify(postingRepository, never()).save(any(Posting.class));
    }

    // Test for posting deletion when posting ID is null.
    @Test
    void testDeletePosting_ByPostingId_NullId() {
        Long postingId = null;

        assertThrows(IllegalArgumentException.class, () -> postingServices.deletePosting(postingId));

        verify(postingRepository, never()).save(any(Posting.class));
    }
}