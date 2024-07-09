package com.project.first.redMath.transaction;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

enum Banks {
    FAISAL_BANK,
    HABIB_BANK_LIMITED,
    SADA_PAY,
    NAYA_PAY,
    MOBILINK_MICROFINANCE_BANK
}

@Entity
@Getter
@Setter
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private double amount;
    private LocalDateTime transaction_date;
    private int from_account;
    @Enumerated(EnumType.STRING)
    private Banks to_bank;
}
