package com.the285.nbbang.biz.dto.user;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends CrudRepository<User, UUID>, UserRepositoryCustom {
    Optional<User> findByEmailIgnoreCase(String email);
    // You could have used findByEmail as well, but chose findByEmailIgnoreCase to make the query case insensitive.
    // As the return type, use Optional<User>, since the user might or might not be there.
    // You could also use User.
    // In that case, Spring Data would return a null refrence when there is no user with the given email address.

    // Since Java 8, Hava has an Optional type that is intended to be used as a return type of a method.
    // It signals to the caller that an actual value might be present or not.
    // It helps to avoid having a NullPointerException by making it explicit that a value might not be there.
}
