package com.adjose.bank.action;

import com.adjose.bank.dao.UserRepository;
import com.adjose.bank.entity.UserProfile;
import com.adjose.bank.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class UserProfileController {

    private UserRepository userRepository;

    @Autowired
    public UserProfileController(final UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PreAuthorize("hasAnyAuthority('ADMINISTRATOR', 'CUSTOMER')")
    @GetMapping("/v1/userprofiles")
    public UserProfile getMyUserProfile(Authentication authentication, Principal principal) {

        final String username = principal.getName();
        return userRepository.findById(username)
                .map(user -> user.getUserProfile())
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + username));
    }

}
