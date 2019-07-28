package com.adjose.bank.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.OptimisticLockType;
import org.hibernate.annotations.OptimisticLocking;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

@Data
@EqualsAndHashCode(of = "accountNumber")
@Entity(name = "accounts")
@OptimisticLocking(type = OptimisticLockType.DIRTY)
@DynamicUpdate
public class Account extends AuditEntity {

    @Id
    @Size(max = 36)
    @Column(name = "account_number")
    private String accountNumber;

    @NotNull
    private String username;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "username", nullable = false, insertable = false, updatable = false)
    @JsonIgnore
    private UserProfile userProfile;

    @Enumerated(EnumType.STRING)
    private Currency currency;

    @NotNull
    private BigDecimal balance;

}
