package com.project.first.redMath.account;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int account_number;
    @Column
    private String account_holder_name;
    @Column
    private Double balance;
    @Column
    private LocalDateTime created_at;
    @Column
    private LocalDateTime updated_at;
}