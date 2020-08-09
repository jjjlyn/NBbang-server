package com.the285.nbbang.user.web;

import com.the285.nbbang.infrastructure.security.ApplicationUserDetails;
import com.the285.nbbang.user.User;
import com.the285.nbbang.user.UserNotFoundException;
import com.the285.nbbang.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
// Set the base route for all methods in this class to /api/user.
public class UserRestController {

    private final UserService mUserService;

    @Autowired
    public UserRestController(UserService userService){
        this.mUserService = userService;
    }

    @GetMapping("/me")
    // appended to the base route of the class, so the full path will be /api/users/me
    public UserDTO currentUser(@AuthenticationPrincipal ApplicationUserDetails userDetails){

        User user = mUserService.getUser(userDetails.getUserId())
                .orElseThrow(()-> new UserNotFoundException(userDetails.getUserId()));
        // Ask the UserService for the user that matches the given ID.

        return UserDTO.fromUser(user);
        // Convert the User entity into a UserDTO object for serialization to JSON.
    }
}
