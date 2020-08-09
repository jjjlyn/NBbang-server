package com.the285.nbbang;

import com.the285.nbbang.biz.dto.user.UserService;
import com.the285.nbbang.infrastructure.SpringProfiles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

/**
 * Try to get an access token from it.
 * Although you won't be able to do anything with the token
 * since you have not written a REST controller yet.
 * For this, you just need to wire up a bit more infrastructure.
 *
 * Implements the ApplicationRunner interface.
 * Spring Boot will run all classes that have this interface after the application starts,
 * a perfect time to add your test user.
 *
 * The response contains an access_token as well as a refresh_token.
 * The access_token is what you need to use in your request.
 * However, the access_token expires after a configurable amount of time.
 * This is indicated by the expires_in field
 * that returns how many seconds the access_token can still be used.
 * When the access_token expires,
 * the refresh_token can be used to obtain a new access_token without the need for the user's password.
 *
 * Note that the refresh_token can also expire (or be revoked at the server).
 * In that case, the client application will need to request the user's username and password again
 * to obtain a new access_token and refresh_token from the server.
 */

@Component
// Mark the class with @Component so the component scanning picks it up.
@Profile(SpringProfiles.DEV)
// The @Profile annotation ensures that this ApplicationRunner only executes if the dev profile is active..
public class DevelopmentDbInitializer implements ApplicationRunner {

    private final UserService mUserService;

    @Autowired
    public DevelopmentDbInitializer(UserService userService){
        // Inject the UserService interface, which you will use to create the users.
        this.mUserService = userService;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        createTestUsers();
    }

    private void createTestUsers(){
        // the actual creation of the test user.
        mUserService.createAdmin("prize1142@gmail.com", "admin");
    }
}
