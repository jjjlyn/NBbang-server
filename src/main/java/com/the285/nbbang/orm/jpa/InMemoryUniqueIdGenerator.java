package com.the285.nbbang.orm.jpa;

import java.util.UUID;

public class InMemoryUniqueIdGenerator implements UniqueIdGenerator<UUID> {

    @Override
    public UUID getNextUniqueId() {
        return UUID.randomUUID();
    }
}
