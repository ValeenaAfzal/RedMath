package com.project.first.redMath.user;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.security.PrivateKey;

@Getter
@Setter
@Entity
@Table
public class user {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column
    private  String FirstName;
    @Column
    private String LastName;
    @Column
    private String Email;
}
