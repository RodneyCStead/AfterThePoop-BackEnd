package com.keyin.domain.Postings;

import com.keyin.domain.Product.Product;
import com.keyin.domain.Product.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PostingServices {

    @Autowired
    private PostingRepository postingRepository;

    @Autowired
    private ProductRepository productRepository;

    public Iterable<Posting> createPostings(List<Posting> postings, String sellerId) {
        for (Posting posting : postings) {
            List<Product> products = new ArrayList<>();
            for (Long productId : posting.getProductId()) {
                Product product = productRepository.findById(productId)
                        .orElseThrow(() -> new RuntimeException("Product not found"));
                products.add(product);
            }
            posting.setProducts(products);
            posting.setSellerId(sellerId);
        }
        return postingRepository.saveAll(postings);
    }

    public Iterable<Posting> getPostings() {
        return postingRepository.findAll();
    }

    public Iterable<Posting> getPostingsBySellerId(String sellerId) {
        return postingRepository.findBySellerId(sellerId);
    }

    public Iterable<Posting> searchByNPercent(double npercent) {
        return postingRepository.findByNPercent(npercent);
    }

    public Iterable<Posting> searchByKPercent(double kpercent) {
        return postingRepository.findByKPercent(kpercent);
    }

    public Iterable<Posting> searchByPPercent(double ppercent) {
        return postingRepository.findByPPercent(ppercent);
    }

    public Iterable<Posting> searchByPrice(double price) {
        return postingRepository.findByPrice(price);
    }
}