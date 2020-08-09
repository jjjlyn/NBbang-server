package com.the285.nbbang.infrastructure.security;

import com.the285.nbbang.biz.dto.user.User;
import com.the285.nbbang.biz.dto.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
// Spring's component scanning to create a singleton instance of this class
public class ApplicationUserDetailsService implements UserDetailsService {

    private final UserRepository mUserRepository;

    @Autowired
    public ApplicationUserDetailsService(UserRepository userRepository) {
        // You inject UserRepository into your class since you need it.
        this.mUserRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = mUserRepository
                .findByEmailIgnoreCase(username)
                .orElseThrow(() -> new UsernameNotFoundException(String.format("User with email %s could not be found", username)));
        // Since you get an Optional, you can use orElseThrow to return the value if it is present or throw an exception if it is not present.
        return new ApplicationUserDetails(user);
        // Wrap your User object in the ApplicationUserDetails object and return it for Spring Security to further handle it.
    }
}
