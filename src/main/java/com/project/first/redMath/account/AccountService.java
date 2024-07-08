package com.project.first.redMath.account;

import org.slf4j.LoggerFactory;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;
import org.springframework.security.core.context.SecurityContextHolder;
@Service
public class AccountService {

    private final AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public Account create(Account account) {
    //   String username = SecurityContextHolder.getContext().getAuthentication().getName();
        account.setCreated_at(LocalDateTime.now());
        account.setUpdated_at(LocalDateTime.now());
        return accountRepository.save(account);
    }

    public Optional<Account> findById(int accountID) {
        return accountRepository.findById(accountID);
    }

    public List<Account> findAll(Integer page, Integer size) {
        if (page < 0) {
            page = 0;
        }
        if (size > 1000) {
            size = 1000;
        }
        return accountRepository.findAll(PageRequest.of(page, size)).getContent();
    }

    public Account updateAccount(Account updatedAccount) {

        return accountRepository.save(updatedAccount);
    }

    public Account partialUpdateAccount(Account updatedAccount) {
        return accountRepository.save(updatedAccount);
    }

    public void deleteAccount(int id) {
        accountRepository.deleteById(id);
    }

}
