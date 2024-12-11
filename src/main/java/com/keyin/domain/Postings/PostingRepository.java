package com.keyin.domain.Postings;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import java.util.Optional;

public interface PostingRepository extends CrudRepository<Posting, Long> {
    Iterable<Posting> findByQuantityGreaterThan(int quantity);
    Iterable<Posting> findBySellerIdAndQuantityGreaterThan(String sellerId, int quantity);

    @Query("SELECT p FROM Posting p WHERE p.product.nPercent = :nPercent AND p.quantity > :quantity")
    Iterable<Posting> findByNPercentAndQuantityGreaterThan(@Param("nPercent") double nPercent, @Param("quantity") int quantity);

    @Query("SELECT p FROM Posting p WHERE p.product.kPercent = :kPercent AND p.quantity > :quantity")
    Iterable<Posting> findByKPercentAndQuantityGreaterThan(@Param("kPercent") double kPercent, @Param("quantity") int quantity);

    @Query("SELECT p FROM Posting p WHERE p.product.pPercent = :pPercent AND p.quantity > :quantity")
    Iterable<Posting> findByPPercentAndQuantityGreaterThan(@Param("pPercent") double pPercent, @Param("quantity") int quantity);

    Iterable<Posting> findByPriceAndQuantityGreaterThan(double price, int quantity);

    @Query("SELECT p FROM Posting p WHERE p.product.nPercent = :nPercent AND p.product.kPercent = :kPercent AND p.product.pPercent = :pPercent")
    Optional<Posting> findByNutrients(@Param("nPercent") double nPercent, @Param("kPercent") double kPercent, @Param("pPercent") double pPercent);
}
