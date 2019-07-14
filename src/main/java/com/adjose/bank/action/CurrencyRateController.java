package com.adjose.bank.action;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CurrencyRateController {

    // todo to be implemented
    @GetMapping("/v1/rates/{currencyCode}")
    public String getCurrencyRate(@PathVariable final String currencyCode) {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return "currency code: " + currencyCode + " (Authenticated= " + authentication.getName() + ")";
    }

}
