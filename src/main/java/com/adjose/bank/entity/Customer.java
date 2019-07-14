package com.adjose.bank.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Data
@Entity(name = "customers")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    // fixme OneToOne relationship
    @OneToOne
//    @JoinColumn(name = "user_username")
    private User user;

    private String email;

//    private String accountNo;

}
