package com.the285.nbbang.biz.dto.user;

import com.the285.nbbang.orm.jpa.AbstractEntityId;

import java.util.UUID;

public class UserId extends AbstractEntityId<UUID> {

    protected UserId(){
        // Hibernate needs the protected no-args constructor to work.
    }

    public UserId(UUID id){
        // This is the constructor that the application code should use.
        super(id);
    }
}
