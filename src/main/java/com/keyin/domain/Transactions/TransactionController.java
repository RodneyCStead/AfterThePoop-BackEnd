package com.keyin.domain.Transactions;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/buy")
@CrossOrigin(origins = "http://localhost:3000")
public class TransactionController {

    @Autowired
    private TransactionServices transactionServices;

    @PostMapping
    public Transaction createTransaction(@RequestParam Long postingId, @RequestParam String sellerId, @RequestParam String buyerId, @RequestParam int quantity) {
        return transactionServices.createTransaction(postingId, sellerId, buyerId, quantity);
    }

    @GetMapping
    public Iterable<Transaction> getAllTransactions() {
        return transactionServices.getTransactions();
    }

    @GetMapping("/transaction")
    public Transaction getTransactionById(@RequestParam Long transactionId) {
        return transactionServices.getTransactionById(transactionId);
    }
}
