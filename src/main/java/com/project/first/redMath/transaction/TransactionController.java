package com.project.first.redMath.transaction;

import com.project.first.redMath.account.Account;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;

@RestController
public class TransactionController {

    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService){
        this.transactionService=transactionService;
    }

    @PostMapping("/RedMath/Trans")
    public ResponseEntity<CompletableFuture<Transaction>>createTransaction(@RequestBody Transaction transaction) throws Exception{
        CompletableFuture<Transaction> future = transactionService.create(transaction);
        Transaction createdTransaction = future.get();
        if (createdTransaction != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(future);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
}
