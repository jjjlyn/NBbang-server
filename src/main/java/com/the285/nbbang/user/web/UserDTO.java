package com.the285.nbbang.user.web;

import com.the285.nbbang.user.User;
import com.the285.nbbang.user.UserId;
import com.the285.nbbang.user.UserRole;
import lombok.Value;

import java.util.Set;

@Value
public class UserDTO {
    private final UserId id;
    private final String email;
    private final Set<UserRole> roles;

    public static UserDTO fromUser(User user){
        return new UserDTO(user.getId(), user.getEmail(), user.getRoles());
    }
}
