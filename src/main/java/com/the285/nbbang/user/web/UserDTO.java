package com.the285.nbbang.user.web;

import com.the285.nbbang.user.User;
import com.the285.nbbang.user.UserId;
import com.the285.nbbang.user.UserRole;
import lombok.Getter;
import lombok.Value;

import java.util.Set;

@Value
@Getter
public class UserDTO {
    UserId id;
    String email;
    Set<UserRole> roles;

    public UserDTO(UserId id, String email, Set<UserRole> roles){
        this.id = id;
        this.email = email;
        this.roles = roles;
    }

    public static UserDTO fromUser(User user){
        return new UserDTO(user.getId(), user.getEmail(), user.getRoles());
    }
}
