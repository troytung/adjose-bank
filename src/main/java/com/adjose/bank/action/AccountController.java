package com.adjose.bank.action;

import com.adjose.bank.dao.AccountRepository;
import com.adjose.bank.dao.UserRepository;
import com.adjose.bank.entity.Account;
import com.adjose.bank.entity.Currency;
import com.adjose.bank.exception.BadRequestException;
import com.adjose.bank.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;
import java.util.UUID;

@RestController
public class AccountController {

    private UserRepository userRepository;
    private AccountRepository accountRepository;

    @Autowired
    public AccountController(final UserRepository userRepository,
                             final AccountRepository accountRepository) {
        this.userRepository = userRepository;
        this.accountRepository = accountRepository;
    }

    @PreAuthorize("hasAuthority('CUSTOMER')")
    @PostMapping("/v1/accounts")
    @ResponseStatus(code = HttpStatus.CREATED)
    public Account createAccount(@RequestParam Currency currency, Principal principal) {

        return userRepository.findById(principal.getName()).map(user -> {

            if (accountRepository.existsByUserProfileAndCurrency(user.getUserProfile(), currency)) {
                throw new BadRequestException("Account with " + currency + " currency exists");
            }

            final Account account = new Account();
            account.setAccountNumber(UUID.randomUUID().toString());
            account.setUserProfile(user.getUserProfile());
            account.setCurrency(currency);
            return accountRepository.save(account);
        }).orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + principal.getName()));
    }

    @PreAuthorize("hasAuthority('CUSTOMER')")
    @GetMapping("/v1/accounts")
    public List<Account> listAccounts(Principal principal) {

        return userRepository.findById(principal.getName()).map(user -> user.getUserProfile().getAccounts())
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + principal.getName()));
    }

}
