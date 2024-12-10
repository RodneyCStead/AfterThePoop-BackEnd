package com.keyin.domain.Postings;

import com.keyin.domain.Product.Product;
import com.keyin.domain.Product.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class PostingServices {

    @Autowired
    private PostingRepository postingRepository;

    @Autowired
    private ProductRepository productRepository;

    public Posting createPosting(Posting posting, String sellerId) {
        Product product = posting.getProduct();
        Optional<Product> existingProduct = Optional.ofNullable(productRepository.findByProductName(product.getProductName()));
        if (existingProduct.isPresent()) {
            throw new IllegalArgumentException("Product with name " + product.getProductName() + " already exists.");
        }
        Product savedProduct = productRepository.save(product);
        posting.setProductId(savedProduct.getProductId());
        posting.setSellerId(sellerId);
        postingRepository.save(posting);
        return posting;
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