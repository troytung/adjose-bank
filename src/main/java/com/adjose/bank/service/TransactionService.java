package com.adjose.bank.service;

import com.adjose.bank.dao.jpa.AccountRepository;
import com.adjose.bank.dao.jpa.TransactionRepository;
import com.adjose.bank.entity.Account;
import com.adjose.bank.entity.transaction.Deposit;
import com.adjose.bank.entity.transaction.TransferIn;
import com.adjose.bank.entity.transaction.TransferOut;
import com.adjose.bank.entity.transaction.Withdrawal;
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

    @Transactional
    public Withdrawal withdraw(final Account account, final BigDecimal amount) {

        account.setBalance(account.getBalance().subtract(amount));
        accountRepository.save(account);
        final Withdrawal withdrawal = new Withdrawal();
        withdrawal.setTransactionId(UUID.randomUUID().toString());
        withdrawal.setTransactionDate(new Date());
        withdrawal.setAccount(account);
        withdrawal.setAmount(amount);
        return transactionRepository.save(withdrawal);
    }

    @Transactional
    public TransferOut transfer(final Account fromAccount, final Account toAccount,
                                final BigDecimal amount) {

        fromAccount.setBalance(fromAccount.getBalance().subtract(amount));
        accountRepository.save(fromAccount);
        toAccount.setBalance(toAccount.getBalance().add(amount));
        accountRepository.save(toAccount);

        final Date transactionDate = new Date();
        final TransferOut transferOut = new TransferOut();
        final TransferIn transferIn = new TransferIn();
        final String transferOutTransactionId = UUID.randomUUID().toString();
        final String transferInTransactionId = UUID.randomUUID().toString();
        transferOut.setTransactionId(transferOutTransactionId);
        transferOut.setTransactionDate(transactionDate);
        transferOut.setAccount(fromAccount);
        transferOut.setAmount(amount);
        transferOut.setToAccount(toAccount);
        transactionRepository.save(transferOut);
        transferIn.setTransactionId(transferInTransactionId);
        transferIn.setTransactionDate(transactionDate);
        transferIn.setAccount(toAccount);
        transferIn.setAmount(amount);
        transferIn.setFromAccount(fromAccount);
        transferIn.setInverseTransactionId(transferOutTransactionId);
        transactionRepository.save(transferIn);

        transferOut.setInverseTransactionId(transferInTransactionId);
        return transactionRepository.save(transferOut);
    }

}
