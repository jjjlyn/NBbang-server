package com.the285.nbbang.user;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends CrudRepository<User, UserId>, UserRepositoryCustom {
    Optional<User> findByEmailIgnoreCase(String email);

    // Since Java 8, Java has an Optional type that is intended to be used as a return type of a method.
    // It signals to the caller that an actual value might be present or not.
    // It helps to avoid having a NullPointerException by making it explicit that a value might not be there.
}
