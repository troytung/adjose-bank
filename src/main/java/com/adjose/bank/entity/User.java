package com.adjose.bank.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.Set;

@Data
@EqualsAndHashCode(exclude = "authorities")
@Entity(name = "users")
public class User {

    @Id
    private String username;

    @JsonIgnore
    private String password;

    private boolean enabled;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Authority> authorities;

}
