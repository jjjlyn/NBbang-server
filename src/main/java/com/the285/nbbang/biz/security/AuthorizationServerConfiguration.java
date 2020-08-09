package com.the285.nbbang.biz.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;

import static com.the285.nbbang.biz.security.ResourceServerConfiguration.RESOURCE_ID;

@Configuration
// ensures that you have an authorization server started
@EnableAuthorizationServer
public class AuthorizationServerConfiguration extends AuthorizationServerConfigurerAdapter {

    @Autowired
    private AuthenticationManager mAuthenticationManager;

    // the contact point btw your application and Spring Security.
    // You will have to create a class that implements this interface
    // which will use the previously created UserRepository to know what users you have in your application
    @Autowired
    private UserDetailsService mUserDetailsService;

    // You obviously should not store user passwords in plain text.
    // This example uses an implementation of PasswordEncoder to encrept passwords
    // when storing them in the database.
    // PasswordEncoder hashes the password so that there is no way to recover the original password,
    // even when the complete database leaks out.
    // Spring Security needs access to the same class to be able to validate passwords
    @Autowired
    private PasswordEncoder mPasswordEncoder;

    // what Spring will use to store the generated access tokens
    @Autowired
    private TokenStore mTokenStore;

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security.passwordEncoder(mPasswordEncoder);
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory()
                .withClient("nbbang-mobile-client")
                .authorizedGrantTypes("password", "refresh_token")
                .scopes("mobile_app")
                .resourceIds(RESOURCE_ID)
                .secret(mPasswordEncoder.encode("ccUyb6vS4s8nxfbKPCrN"));
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.tokenStore(mTokenStore)
                .authenticationManager(mAuthenticationManager)
                .userDetailsService(mUserDetailsService);
    }
}
