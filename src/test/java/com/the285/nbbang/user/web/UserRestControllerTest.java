package com.the285.nbbang.user.web;

import com.the285.nbbang.infrastructure.SpringProfiles;
import com.the285.nbbang.user.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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