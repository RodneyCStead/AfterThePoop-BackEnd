package com.keyin.domain.Postings;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/fertilizer")
public class PostingController {

    @Autowired
    private PostingServices postingServices;


    @PostMapping
    public Iterable<Posting> createPostings(@RequestBody List<Posting> postings, @RequestParam(defaultValue = "unknownUser") String sellerId) {
        return postingServices.createPostings(postings, sellerId);
    }

    @PostMapping("/buy")
    public void buyFertilizer(@RequestParam Long postingId, @RequestParam int quantity) {
        postingServices.updatePostingQuantity(postingId, quantity);
    }
}