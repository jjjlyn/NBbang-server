package com.the285.nbbang.infrastructure.security;

import com.the285.nbbang.user.Users;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class StubUserDetailsService implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        switch (username){
            case Users.ADMIN_EMAIL :
                return new ApplicationUserDetails(Users.admin());
            case Users.GENERAL_EMAIL :
                return new ApplicationUserDetails(Users.general());
            default:
                throw new UsernameNotFoundException(username);
        }
    }
}
