package com.adjose.bank.dao.jpa;

import com.adjose.bank.entity.Account;
import com.adjose.bank.entity.Currency;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional(readOnly = true)
public interface AccountRepository extends CrudRepository<Account, String> {

    boolean existsByUsernameAndCurrency(String username, Currency currency);

    Optional<Account> findByUsernameAndAccountNumber(String username, String accountNumber);

    List<Account> findByUsername(String username);

}
