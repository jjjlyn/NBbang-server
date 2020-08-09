package com.the285.nbbang.infrastructure.security;

import com.the285.nbbang.user.User;
import com.the285.nbbang.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class ApplicationUserDetailsService implements UserDetailsService {

    private final UserRepository mUserRepository;

    @Autowired
    public ApplicationUserDetailsService(UserRepository userRepository) {
        this.mUserRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = mUserRepository
                .findByEmailIgnoreCase(username)
                .orElseThrow(() -> new UsernameNotFoundException(String.format("User with email %s could not be found", username)));
        return new ApplicationUserDetails(user);
        // Wrap User object in the ApplicationUserDetails object and return it for Spring Security to further handle it.
    }
}
