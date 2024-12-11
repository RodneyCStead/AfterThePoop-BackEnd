package com.keyin.domain.Postings;

import com.keyin.domain.Product.Product;
import com.keyin.domain.Product.ProductRepository;
import com.keyin.domain.Transactions.TransactionRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class PostingServices {

    @Autowired
    private PostingRepository postingRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private JdbcTemplate jdbcTemplate;


    @Transactional
    public Posting createPosting(Posting posting, String sellerId) {
        Product product = posting.getProduct();

        Optional<Product> existingProduct = Optional.ofNullable(productRepository.findByProductName(product.getProductName()));

        if (existingProduct.isPresent()) {
            Optional<Posting> existingPosting = postingRepository.findByNutrients(
                    product.getNPercent(), product.getKPercent(), product.getPPercent()
            );

            if (existingPosting.isPresent()) {
                Posting foundPosting = existingPosting.get();
                foundPosting.setQuantity(foundPosting.getQuantity() + posting.getQuantity());
                postingRepository.save(foundPosting);
                return foundPosting;
            } else {
                Product existingProd = existingProduct.get();
                posting.setProduct(existingProd);
                posting.setProductId(existingProd.getProductId());
                posting.setSellerId(sellerId);
                postingRepository.save(posting);
                return posting;
            }
        } else {
            Product savedProduct = productRepository.save(product);
            posting.setProduct(savedProduct);
            posting.setProductId(savedProduct.getProductId());
            posting.setSellerId(sellerId);
            postingRepository.save(posting);
            return posting;
        }
    }

    public Iterable<Posting> getPostings() {
        return postingRepository.findByQuantityGreaterThan(0);
    }

    public Iterable<Posting> getPostingsBySellerId(String sellerId) {
        return postingRepository.findBySellerIdAndQuantityGreaterThan(sellerId, 0);
    }

    public Iterable<Posting> searchByNPercent(double npercent) {
        return postingRepository.findByNPercentAndQuantityGreaterThan(npercent, 0);
    }

    public Iterable<Posting> searchByKPercent(double kpercent) {
        return postingRepository.findByKPercentAndQuantityGreaterThan(kpercent, 0);
    }

    public Iterable<Posting> searchByPPercent(double ppercent) {
        return postingRepository.findByPPercentAndQuantityGreaterThan(ppercent, 0);
    }

    public Iterable<Posting> searchByPrice(double price) {
        return postingRepository.findByPriceAndQuantityGreaterThan(price, 0);
    }

    @Transactional
    public void deletePosting(Long postingId) {
        Posting posting = postingRepository.findById(postingId)
                .orElseThrow(() -> new RuntimeException("Posting not found"));
        posting.setQuantity(0);
        postingRepository.save(posting);
    }

    private void resetPostingSequenceIfEmpty() {
        String checkIfEmptyQuery = "SELECT COUNT(*) FROM posting";
        Integer count = jdbcTemplate.queryForObject(checkIfEmptyQuery, Integer.class);

        if (count != null && count == 0) {
            String resetSequenceQuery = "ALTER TABLE posting AUTO_INCREMENT = 1";
            jdbcTemplate.execute(resetSequenceQuery);

            String updateSequenceTableQuery = "UPDATE posting_sequence SET next_val = 1";
            jdbcTemplate.execute(updateSequenceTableQuery);
        }
    }
}