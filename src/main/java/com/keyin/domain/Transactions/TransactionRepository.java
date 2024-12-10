package com.keyin.domain.Transactions;

import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

public interface TransactionRepository extends CrudRepository<Transaction, Long> {

    @Transactional
    void deleteByPosting_PostingId(Long postingId);
}
