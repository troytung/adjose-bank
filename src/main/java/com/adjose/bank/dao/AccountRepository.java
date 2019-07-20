package com.adjose.bank.dao;

import com.adjose.bank.entity.Account;
import com.adjose.bank.entity.Currency;
import com.adjose.bank.entity.UserProfile;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends CrudRepository<Account, String> {

    boolean existsByUserProfileAndCurrency(UserProfile userProfile, Currency currency);

}