package com.keyin.domain.PostingsTests;

import com.keyin.domain.Postings.Posting;
import com.keyin.domain.Postings.PostingController;
import com.keyin.domain.Postings.PostingServices;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import java.util.Collections;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(PostingController.class)
class PostingEndpointsTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PostingServices postingServices;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    // Test the createPosting endpoint
    @Test
    void testCreatePosting() throws Exception {
        Posting posting = new Posting();
        posting.setPostingId(1L);
        when(postingServices.createPosting(any(Posting.class), anyString())).thenReturn(posting);

        mockMvc.perform(post("/fertilizer")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"id\":1,\"product\":{\"id\":1},\"quantity\":5}")
                        .param("sellerId", "testSeller"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.postingId").value(1));

        verify(postingServices, times(1)).createPosting(any(Posting.class), eq("testSeller"));
    }

    // Test the getPostings endpoint
    @Test
    void testGetPostings() throws Exception {
        when(postingServices.getPostings()).thenReturn(Collections.emptyList());

        mockMvc.perform(get("/fertilizer"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray());

        verify(postingServices, times(1)).getPostings();
    }

    // Test the getPostingsBySellerId endpoint
    @Test
    void testGetPostingsBySellerId() throws Exception {
        when(postingServices.getPostingsBySellerId(anyString())).thenReturn(Collections.emptyList());

        mockMvc.perform(get("/fertilizer/seller")
                        .param("sellerId", "testSeller"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray());

        verify(postingServices, times(1)).getPostingsBySellerId("testSeller");
    }

    // Test the getPostingsByNPercent endpoint
    @Test
    void testSearchByNPercent() throws Exception {
        when(postingServices.searchByNPercent(anyDouble())).thenReturn(Collections.emptyList());

        mockMvc.perform(get("/fertilizer/searchByNPercent")
                        .param("npercent", "10.0"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray());

        verify(postingServices, times(1)).searchByNPercent(10.0);
    }

    // Test the getPostingsByKPercent endpoint
    @Test
    void testSearchByKPercent() throws Exception {
        when(postingServices.searchByKPercent(anyDouble())).thenReturn(Collections.emptyList());

        mockMvc.perform(get("/fertilizer/searchByKPercent")
                        .param("kpercent", "5.0"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray());

        verify(postingServices, times(1)).searchByKPercent(5.0);
    }

    // Test the getPostingsByPPercent endpoint
    @Test
    void testSearchByPPercent() throws Exception {
        when(postingServices.searchByPPercent(anyDouble())).thenReturn(Collections.emptyList());

        mockMvc.perform(get("/fertilizer/searchByPPercent")
                        .param("ppercent", "3.0"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray());

        verify(postingServices, times(1)).searchByPPercent(3.0);
    }

    // Test the getPostingsByPrice endpoint
    @Test
    void testSearchByPrice() throws Exception {
        when(postingServices.searchByPrice(anyDouble())).thenReturn(Collections.emptyList());

        mockMvc.perform(get("/fertilizer/searchByPrice")
                        .param("price", "100.0"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray());

        verify(postingServices, times(1)).searchByPrice(100.0);
    }

    // Test the deletePostingById endpoint
    @Test
    void testDeletePosting() throws Exception {
        doNothing().when(postingServices).deletePosting(anyLong());

        mockMvc.perform(post("/fertilizer/Delete")
                        .param("postingId", "1"))
                .andExpect(status().isOk());

        verify(postingServices, times(1)).deletePosting(1L);
    }
}
