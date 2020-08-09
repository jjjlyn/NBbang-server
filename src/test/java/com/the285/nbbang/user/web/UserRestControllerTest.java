package com.the285.nbbang.user.web;

import com.the285.nbbang.infrastructure.SpringProfiles;
import com.the285.nbbang.infrastructure.security.StubUserDetailsService;
import com.the285.nbbang.infrastructure.security.config.OAuthSecurityConfig;
import com.the285.nbbang.infrastructure.security.config.SecurityConfiguration;
import com.the285.nbbang.user.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@ExtendWith(SpringExtension.class)
@WebMvcTest(UserRestController.class)
// Controller you want to test
@ActiveProfiles(SpringProfiles.TEST)
class UserRestControllerTest {

    @Autowired
    private MockMvc mMockMvc;

    @MockBean
    private UserService mService;

    @Test
    public void givenNotAuthenticated_whenAskingMyDetails_forbidden() throws Exception {
        mMockMvc.perform(get("/api/users/me"))
                .andExpect(status().isUnauthorized());
    }

//    @TestConfiguration
//    @Import(OAuthSecurityConfig.class)
//    static class TestConfig {
//
//        @Bean
//        public UserDetailsService userDetailsService(){
//            return new StubUserDetailsService();
//        }
//
//        @Bean
//        public TokenStore tokenStore(){
//            return new InMemoryTokenStore();
//        }
//
//        @Bean
//        public SecurityConfiguration securityConfiguration(){
//            return new SecurityConfiguration();
//        }
//    }
}