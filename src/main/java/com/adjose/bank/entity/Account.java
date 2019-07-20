package com.adjose.bank.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Size;

@Data
@EqualsAndHashCode(of = "accountNumber")
@Entity(name = "accounts")
public class Account extends AuditEntity {

    @Id
    @Size(max = 36)
    @Column(name = "account_number")
    private String accountNumber;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "username", nullable = false)
    @JsonIgnore
    private UserProfile userProfile;

    @Enumerated(EnumType.STRING)
    private Currency currency;

    // todo add transactions
//    private List<Account> accounts;

}
