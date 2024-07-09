package com.project.first.redMath.transaction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class TransactionDataService {
    private final JdbcTemplate jdbcTemplate;
    public TransactionDataService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Transaction> findByTransactionDateAfter(LocalDateTime transactionDate) {
        String sql = "SELECT * FROM transaction WHERE transaction_date > ?";
        return jdbcTemplate.query(sql, new Object[]{transactionDate}, new BeanPropertyRowMapper<>(Transaction.class));
    }

}
