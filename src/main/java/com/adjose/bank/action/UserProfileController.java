package com.adjose.bank.action;

import com.adjose.bank.dao.jpa.UserProfileRepository;
import com.adjose.bank.entity.UserProfile;
import com.adjose.bank.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class UserProfileController {

    private UserProfileRepository userProfileRepository;

    @Autowired
    public UserProfileController(final UserProfileRepository userProfileRepository) {
        this.userProfileRepository = userProfileRepository;
    }

    @PreAuthorize("hasAnyAuthority('ADMINISTRATOR', 'CUSTOMER')")
    @GetMapping("/v1/userprofiles")
    public UserProfile getMyUserProfile(Authentication authentication, Principal principal) {

        final String username = principal.getName();
        return userProfileRepository.findById(username)
                .orElseThrow(() -> new ResourceNotFoundException("User profile not found"));
    }

    @PreAuthorize("hasAuthority('CUSTOMER')")
    @PutMapping("/v1/userprofiles")
    @ResponseStatus(code = HttpStatus.OK)
    public UserProfile updateUserProfile(@RequestParam String email, @RequestParam String phoneNumber,
                                         Principal principal) {

        final String username = principal.getName();
        return userProfileRepository.findById(username).map(userProfile -> {
            userProfile.setEmail(email);
            userProfile.setPhoneNumber(phoneNumber);
            return userProfileRepository.save(userProfile);
        }).orElseThrow(() -> new ResourceNotFoundException("User profile not found"));
    }

}
