package com.project.first.redMath.account;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Optional;

@RestController
public class AccountController {
    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping("/redMath/{id}")
    public ResponseEntity<Account> get(@PathVariable("id") int id) {
        Optional<Account> news = accountService.findById(id);
        if (news.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(news.get());
    }

    @GetMapping("/redMath/")
    public ResponseEntity<List<Account>> get(@RequestParam(name = "page", defaultValue = "0") Integer page, @RequestParam(name = "page", defaultValue = "1000") Integer size) {
        return ResponseEntity.ok(accountService.findAll(page, size));
    }

    @PostMapping("/redMath/")
    public ResponseEntity<Account> createAccount(@RequestBody Account account) {
        Account createdAccount = accountService.create(account);
        if (createdAccount != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(createdAccount);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @DeleteMapping("/redMath/{id}")
    public ResponseEntity<Void> deleteAccount(@PathVariable("id") int id) {
        Optional<Account> account = accountService.findById(id);
        if (account.isPresent()) {
            accountService.deleteAccount(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/redMath/{id}")
    public ResponseEntity<Account> updateAccount(@PathVariable("id") int id, @RequestBody Account updatedAccount) {
        Optional<Account> account = accountService.findById(id);
        if (account.isPresent()) {
            updatedAccount.setAccount_number(id); // Ensure ID consistency
            Account savedAccount = accountService.updateAccount(updatedAccount);
            return ResponseEntity.ok(savedAccount);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PatchMapping("/redMath/{id}")
    public ResponseEntity<Account> partialUpdateAccount(@PathVariable("id") int id, @RequestBody Account updatedAccount) {
        Optional<Account> account = accountService.findById(id);
        if (account.isPresent()) {
            updatedAccount.setAccount_number(id); // Ensure ID consistency
            Account patchedAccount = accountService.partialUpdateAccount(updatedAccount);
            return ResponseEntity.ok(patchedAccount);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
