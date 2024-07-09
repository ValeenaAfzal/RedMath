package com.project.first.redMath.transaction;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final TransactionDataService transactionDataService;

    public TransactionService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
        this.transactionDataService = null;
    }

    @Autowired
    public TransactionService(TransactionDataService transactionDataService) {
        this.transactionDataService = transactionDataService;
        this.transactionRepository = null;
    }

    @Async
    public CompletableFuture<Transaction> create(Transaction transaction)  throws InterruptedException {
        transaction.setTransaction_date(LocalDateTime.now());
        Transaction savedTransaction = transactionRepository.save(transaction);
        Thread.sleep(1000L);
        return CompletableFuture.completedFuture(savedTransaction);
    }

    @Scheduled(fixedRate = 6000)
    public void sendTransactionEmails() throws  Exception{
        System.out.println("Sending email for transaction: /n/n/n/n/n/n//n/n/n/n/n/n/");
        LocalDateTime oneMinuteAgo = LocalDateTime.now().minusMinutes(1);
        List<Transaction> recentTransactions = transactionDataService.findByTransactionDateAfter(oneMinuteAgo);
        for (Transaction transaction : recentTransactions) {
           // sendEmailToUser(transaction);
        }
    }

}

//ceeb61cc970f4a08bc6a85110fdd2ced