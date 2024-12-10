package com.keyin.domain.Postings;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import java.util.Optional;

public interface PostingRepository extends CrudRepository<Posting, Long> {

    Iterable<Posting> findBySellerId(String sellerId);

    @Query("SELECT p FROM Posting p JOIN p.product prod WHERE prod.nPercent = :npercent AND prod.kPercent = :kpercent AND prod.pPercent = :ppercent")
    Optional<Posting> findByNutrients(@Param("npercent") double npercent, @Param("kpercent") double kpercent, @Param("ppercent") double ppercent);

    @Query("SELECT p FROM Posting p JOIN p.product prod WHERE prod.nPercent = :npercent")
    Iterable<Posting> findByNPercent(@Param("npercent") double npercent);

    @Query("SELECT p FROM Posting p JOIN p.product prod WHERE prod.kPercent = :kpercent")
    Iterable<Posting> findByKPercent(@Param("kpercent") double kpercent);

    @Query("SELECT p FROM Posting p JOIN p.product prod WHERE prod.pPercent = :ppercent")
    Iterable<Posting> findByPPercent(@Param("ppercent") double ppercent);

    Iterable<Posting> findByPrice(double price);
}
