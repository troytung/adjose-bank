package com.adjose.bank.action;

import com.adjose.bank.dao.jpa.AccountRepository;
import com.adjose.bank.entity.Account;
import com.adjose.bank.entity.Currency;
import com.adjose.bank.exception.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.security.Principal;
import java.util.List;
import java.util.UUID;

@RestController
public class AccountController {

    private AccountRepository accountRepository;

    @Autowired
    public AccountController(final AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @PreAuthorize("hasAuthority('CUSTOMER')")
    @PostMapping("/v1/accounts")
    @ResponseStatus(code = HttpStatus.CREATED)
    public Account createAccount(@RequestParam Currency currency, Principal principal) {

        final String username = principal.getName();
        if (accountRepository.existsByUsernameAndCurrency(username, currency)) {
            throw new BadRequestException("Account with " + currency + " currency exists");
        }

        final Account account = new Account();
        account.setAccountNumber(UUID.randomUUID().toString());
        account.setUsername(username);
        account.setCurrency(currency);
        account.setBalance(BigDecimal.ZERO);
        return accountRepository.save(account);
    }

    @PreAuthorize("hasAuthority('CUSTOMER')")
    @GetMapping("/v1/accounts")
    public List<Account> listAccounts(Principal principal) {
        return accountRepository.findByUsername(principal.getName());
    }

}
