package com.adjose.bank.service;

import com.adjose.bank.dao.AccountRepository;
import com.adjose.bank.dao.TransactionRepository;
import com.adjose.bank.entity.Account;
import com.adjose.bank.entity.transaction.Deposit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

@Service
public class TransactionService {

    private AccountRepository accountRepository;
    private TransactionRepository transactionRepository;

    @Autowired
    public TransactionService(final AccountRepository accountRepository,
                              final TransactionRepository transactionRepository) {
        this.accountRepository = accountRepository;
        this.transactionRepository = transactionRepository;
    }

    @Transactional
    public Deposit deposit(final Account account, final BigDecimal amount) {

        account.setBalance(account.getBalance().add(amount));
        accountRepository.save(account);
        final Deposit deposit = new Deposit();
        deposit.setTransactionId(UUID.randomUUID().toString());
        deposit.setTransactionDate(new Date());
        deposit.setAccount(account);
        deposit.setAmount(amount);
        return transactionRepository.save(deposit);
    }

}
