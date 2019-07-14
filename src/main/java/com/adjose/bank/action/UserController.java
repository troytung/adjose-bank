package com.adjose.bank.action;

import com.adjose.bank.dao.UserRepository;
import com.adjose.bank.entity.Authority;
import com.adjose.bank.entity.User;
import com.adjose.bank.security.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

import static java.util.Collections.singleton;

@RestController
public class UserController {

    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserController(final UserRepository userRepository,
                          final PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/v1/users")
    @ResponseStatus(code = HttpStatus.CREATED)
    public User createUser(@RequestParam String username, @RequestParam String password, @RequestParam String email) {

        userRepository.getUserByUsername(username).ifPresent(user -> {
            throw new RuntimeException("User " + username + " exists");
        });

        final User newUser = new User();
        newUser.setUsername(username);
        newUser.setPassword(passwordEncoder.encode(password));
        newUser.setEnabled(true);
        final Authority authority = new Authority();
        authority.setUser(newUser);
        authority.setAuthority(Role.USER.name());
        newUser.setAuthorities(singleton(authority));
        return userRepository.save(newUser);
    }

    // todo to be implemented
    @PreAuthorize("hasAuthority('USER')")
    @GetMapping("/v1/users/{id}")
    public String getUser(@PathVariable String id, Authentication authentication, Principal principal) {
        return "User id: " + id + " (Authenticated= " + authentication.getName() + ")";
    }

    // todo to be implemented
    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/v1/users")
    public String listAllUsers(Authentication authentication, Principal principal) {
        return "Users: (Authenticated= " + authentication.getName() + ", principal= " + principal.getName() + ")";
    }

}
