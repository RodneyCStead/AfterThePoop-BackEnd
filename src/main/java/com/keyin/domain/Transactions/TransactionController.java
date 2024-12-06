package com.keyin.domain.Transactions;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/buy")
public class TransactionController {

    @Autowired
    private TransactionServices transactionServices;

    @PostMapping
    public Transaction createTransaction(@RequestParam Long postingId, @RequestParam String sellerId, @RequestParam String buyerId, @RequestParam int quantity) {
        return transactionServices.createTransaction(postingId, sellerId, buyerId, quantity);
    }

    @GetMapping
    public Iterable<Transaction> getAllTransactions() {
        return transactionServices.getAllTransactions();
    }

    @GetMapping("/transaction")
    public Transaction getTransactionById(@RequestParam Long transactionId) {
        return transactionServices.getTransactionById(transactionId);
    }
}
