package com.adjose.bank.action;

import com.adjose.bank.dao.AccountRepository;
import com.adjose.bank.dao.TransactionRepository;
import com.adjose.bank.dao.UserProfileRepository;
import com.adjose.bank.entity.Account;
import com.adjose.bank.entity.transaction.Deposit;
import com.adjose.bank.exception.BadRequestException;
import com.adjose.bank.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.security.Principal;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;

@RestController
public class TransactionController {

    private UserProfileRepository userProfileRepository;
    private AccountRepository accountRepository;
    private TransactionRepository transactionRepository;

    @Autowired
    public TransactionController(final UserProfileRepository userProfileRepository,
                                 final AccountRepository accountRepository,
                                 final TransactionRepository transactionRepository) {
        this.userProfileRepository = userProfileRepository;
        this.accountRepository = accountRepository;
        this.transactionRepository = transactionRepository;
    }

    @PreAuthorize("hasAuthority('CUSTOMER')")
    @PostMapping("/v1/transactions/deposit")
    @ResponseStatus(code = HttpStatus.CREATED)
    @Transactional
    public Deposit deposit(@RequestParam String accountNumber, @RequestParam BigDecimal amount,
                           Principal principal) {

        return userProfileRepository.findById(principal.getName()).map(userProfile -> {
            final Optional<Account> accountOptional =
                    accountRepository.findByAccountNumberAndUserProfile(accountNumber, userProfile);
            if (accountOptional.isPresent()) {
                final Account account = accountOptional.get();
                account.setBalance(account.getBalance().add(amount));
                accountRepository.save(account);
                final Deposit deposit = new Deposit();
                deposit.setTransactionId(UUID.randomUUID().toString());
                deposit.setTransactionDate(new Date());
                deposit.setAccount(account);
                deposit.setAmount(amount);
                return transactionRepository.save(deposit);
            } else {
                throw new BadRequestException("Account not found");
            }
        }).orElseThrow(() -> new ResourceNotFoundException("User profile not found"));
    }

}
