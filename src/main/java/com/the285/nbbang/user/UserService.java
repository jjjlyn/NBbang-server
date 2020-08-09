package com.the285.nbbang.user;

import java.util.Optional;

public interface UserService {
    User createAdmin(String email, String password);

    Optional<User> getUser(UserId userId);
}
