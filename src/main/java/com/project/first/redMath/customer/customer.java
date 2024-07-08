package com.project.first.redMath.customer;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity(name="customer")
public class customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column
    private  String first_name;
    @Column
    private String last_name;
    @Column
    private String email;
    @Column
    private String cnic;
    @Column
    private LocalDateTime created_at;
    @Column
    private LocalDateTime updated_at;
}
