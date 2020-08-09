package com.the285.nbbang.biz.dto.user;

/**
 * In your unit test, you manually created the primary key by calling UUID,randomUUID().
 * This could be fine for a UUID, but certainly not if you want to use long, for example.
 * For this reason, add a method on the UserRepository that will give you the "next" ID to use
 * if you want to create an entity.
 * Since UserRepository is an interface, you need to do some additional work to make this possible.
 * To get started,you need to create a UserRepositoryCustom interface
 */
public interface UserRepositoryCustom {
    // The nextId method will return a new UserId instance each time it is called.
    UserId nextId();
}
