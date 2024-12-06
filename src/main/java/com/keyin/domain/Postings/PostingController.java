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

    @GetMapping
    public Iterable<Posting> getPostings() {
        return postingServices.getPostings();
    }

    @GetMapping("/seller")
    public Iterable<Posting> getPostingsBySellerId(@RequestParam String sellerId) {
        return postingServices.getPostingsBySellerId(sellerId);
    }

    @GetMapping("/searchByNPercent")
    public Iterable<Posting> searchByNPercent(@RequestParam double npercent) {
        return postingServices.searchByNPercent(npercent);
    }

    @GetMapping("/searchByKPercent")
    public Iterable<Posting> searchByKPercent(@RequestParam double kpercent) {
        return postingServices.searchByKPercent(kpercent);
    }

    @GetMapping("/searchByPPercent")
    public Iterable<Posting> searchByPPercent(@RequestParam double ppercent) {
        return postingServices.searchByPPercent(ppercent);
    }

    @GetMapping("/searchByPrice")
    public Iterable<Posting> searchByPrice(@RequestParam double price) {
        return postingServices.searchByPrice(price);
    }

}