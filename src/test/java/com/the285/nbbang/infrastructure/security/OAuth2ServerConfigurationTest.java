package com.the285.nbbang.infrastructure.security;

import com.the285.nbbang.biz.dto.user.UserService;
import com.the285.nbbang.biz.dto.user.Users;
import com.the285.nbbang.infrastructure.SpringProfiles;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles(SpringProfiles.TEST)
public class OAuth2ServerConfigurationTest {

    @Autowired
    private MockMvc mMockMvc;

    @Autowired
    private UserService mUserService;

    @Test
    public void testGetAccessTokenAsAdmin() throws Exception {
        mUserService.createAdmin(Users.ADMIN_EMAIL, Users.ADMIN_PASSWORD);

        // While everything now works fine, it feels wrong to have your production client_id and client_secret in your unit test.
        // Spring Boot makes externalizing configuration a breeze.
        // You can define a class to hold your own properties and use them like the built-in properties of Spring Boot,
        // like server.port for example.
        String clientId = "test-client-id";
        String clientSecret = "test-client-secret";

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("grant_type", "password");
        params.add("client_id", clientId);
        params.add("client_secret", clientSecret);
        params.add("username", Users.ADMIN_EMAIL);
        params.add("password", Users.ADMIN_PASSWORD);

        mMockMvc.perform(post("/oauth/token")
                            .params(params)
                            .with(httpBasic(clientId, clientSecret))
                            .accept("application/json;charset=UTF-8"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andDo(print())
                .andExpect(jsonPath("access_token").isString())
                .andExpect(jsonPath("token_type").value("bearer"))
                .andExpect(jsonPath("refresh_token").isString())
                .andExpect(jsonPath("expires_in").isNumber())
                .andExpect(jsonPath("scope").value("mobile_app"));
    }
}
