package com.the285.nbbang.infrastructure.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;


@Configuration
public class AuthorizationServerConfiguration extends AuthorizationServerConfigurerAdapter {

    @Autowired
    private AuthenticationManager mAuthenticationManager;

    // the contact point btw your application and Spring Security.
    @Autowired
    private UserDetailsService mUserDetailsService;

    @Autowired
    private PasswordEncoder mPasswordEncoder;

    // what Spring will use to store the generated access tokens
    @Autowired
    private TokenStore mTokenStore;

    @Autowired
    private SecurityConfiguration mSecurityConfiguration;

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security.passwordEncoder(mPasswordEncoder);
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory()
                .withClient(mSecurityConfiguration.getMobileAppClientId())
                .authorizedGrantTypes("password", "refresh_token")
                .scopes("mobile_app")
                .resourceIds(ResourceServerConfiguration.RESOURCE_ID)
                .secret(mPasswordEncoder.encode(mSecurityConfiguration.getMobileAppClientSecret()));
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.tokenStore(mTokenStore)
                .authenticationManager(mAuthenticationManager)
                .userDetailsService(mUserDetailsService);
    }
}
