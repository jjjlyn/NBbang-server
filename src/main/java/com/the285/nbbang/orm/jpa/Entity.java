package com.the285.nbbang.orm.jpa;

public interface Entity<T extends EntityId> {
    /**
     * Interface for entity objects.
     *
     * @param <T> the type of {@link EntityId} that will be used in this entity
     */
    T getId();
}
