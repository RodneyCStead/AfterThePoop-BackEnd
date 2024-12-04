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

    public void updatePostingQuantity(Long postingId, int quantity) {
        Posting posting = postingRepository.findById(postingId).orElseThrow(() -> new RuntimeException("Posting not found"));
        posting.setQuantity(posting.getQuantity() - quantity);
        postingRepository.save(posting);
    }
}