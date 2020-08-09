package com.the285.nbbang.biz.dto.user;

import com.sun.istack.NotNull;
import com.the285.nbbang.orm.jpa.AbstractEntity;
import lombok.*;

import javax.persistence.*;
import java.util.Collections;
import java.util.Set;
import java.util.UUID;

@Entity
// marks the class as a persistable entity for JPA
@Table(name = "nbbang_user")
@Getter
@ToString
public class User extends AbstractEntity<UserId> {

    private String email;
    private String password;

    @ElementCollection(fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    // The roles field is a collection of enum values.
    // @Enumerated(EnumType.STRING) ensures the enum values are stored as string values.
    @NotNull
    private Set<UserRole> roles;

    // Hibernate needs a no-argument constructor.
    // It does not need to be public, so this keeps it protected.
    protected User(){

    }

    public User(UserId id, String email, String password, @NotNull Set<UserRole> roles){
        super(id);
        this.email = email;
        this.password = password;
        this.roles = roles;
    }

    public static User createAdmin(UserId userId, String email, String password){
        return new User(userId, email, password, Collections.singleton(UserRole.ADMIN));
    }

    public static User createGeneral(UserId userId, String email, String password){
        return new User(userId, email, password, Collections.singleton(UserRole.GENERAL));
    }
}