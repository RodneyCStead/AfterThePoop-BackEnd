package com.keyin.domain.Postings;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/fertilizer")
@CrossOrigin(origins = "http://localhost:3000")
public class PostingController {

    @Autowired
    private PostingServices postingServices;

    @PostMapping
    public Posting createPosting(@RequestBody Posting posting, @RequestParam(defaultValue = "unknownUser") String sellerId) {
        return postingServices.createPosting(posting, sellerId);
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

    @PostMapping("/Delete")
    public void deletePosting(@RequestParam Long postingId) {
        postingServices.deletePosting(postingId);
    }

}