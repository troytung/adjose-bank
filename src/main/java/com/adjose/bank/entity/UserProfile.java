package com.adjose.bank.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.OptimisticLockType;
import org.hibernate.annotations.OptimisticLocking;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@EqualsAndHashCode(of = "username")
@Entity(name = "user_profiles")
@OptimisticLocking(type = OptimisticLockType.DIRTY)
@DynamicUpdate
public class UserProfile extends AuditEntity {

    @Id
    private String username;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "username", nullable = false)
    @JsonIgnore
    private User user;

    @NotNull
    @Email
    @Size(max = 100)
    @Column(unique = true)
    private String email;

    @Column(name = "phone_number")
    @Size(max = 15)
    private String phoneNumber;

    @OneToMany(mappedBy = "userProfile", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private List<Account> accounts;

}
