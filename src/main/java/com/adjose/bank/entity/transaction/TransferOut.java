package com.adjose.bank.entity.transaction;

import com.adjose.bank.entity.Account;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Size;

@Getter
@Setter
@Entity
public class TransferOut extends Transaction {

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "to_account_number", nullable = false)
    @JsonIgnore
    private Account toAccount;

    @Size(max = 36)
    @Column(name = "inverse_transaction_id")
    private String inverseTransactionId;

}
