package com.adjose.bank.action;

import com.adjose.bank.dao.UserRepository;
import com.adjose.bank.entity.Authority;
import com.adjose.bank.entity.User;
import com.adjose.bank.entity.UserProfile;
import com.adjose.bank.exception.BadRequestException;
import com.adjose.bank.exception.ResourceNotFoundException;
import com.adjose.bank.security.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

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
    public User createUser(@RequestParam String username, @RequestParam String password,
                           @RequestParam String email, @RequestParam String phoneNumber) {

        if (userRepository.existsById(username)) {
            throw new BadRequestException("User " + username + " exists");
        }

        final User newUser = new User();
        newUser.setUsername(username);
        newUser.setPassword(passwordEncoder.encode(password));
        newUser.setEnabled(true);
        final Authority authority = new Authority();
        authority.setUser(newUser);
        authority.setAuthority(Role.CUSTOMER.name());
        newUser.setAuthorities(singleton(authority));
        final UserProfile userProfile = new UserProfile();
        userProfile.setUsername(username);
        userProfile.setUser(newUser);
        userProfile.setEmail(email);
        userProfile.setPhoneNumber(phoneNumber);
        newUser.setUserProfile(userProfile);
        return userRepository.save(newUser);
    }

    @PreAuthorize("hasAuthority('ADMINISTRATOR')")
    @GetMapping("/v1/users/{username}")
    public User getUser(@PathVariable String username) {

        return userRepository.findById(username)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + username));
    }

    @PreAuthorize("hasAuthority('ADMINISTRATOR')")
    @GetMapping("/v1/users")
    public Page<User> listAllUsers(Pageable pageable) {

        return userRepository.findAll(pageable);
    }

}
