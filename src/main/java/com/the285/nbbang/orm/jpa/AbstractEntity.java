package com.the285.nbbang.orm.jpa;

import com.the285.nbbang.utils.ArtifactForFramework;

import javax.persistence.EmbeddedId;
import javax.persistence.MappedSuperclass;
import java.util.Objects;

import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.base.MoreObjects.toStringHelper;

@MappedSuperclass
public class AbstractEntity<T extends EntityId> implements Entity<T> {

    /**
     * Abstract super class for entities.
     * We are assuming that early primary key generation will be used.
     *
     * @param <T> the type of {@link EntityId} that will be used for this entity
     */

    @EmbeddedId
    private T id;

    @ArtifactForFramework
    protected AbstractEntity(){
    }

    public AbstractEntity(T id){
        this.id = checkNotNull(id);
    }

    @Override
    public T getId() {
        return id;
    }

    @Override
    public boolean equals(Object obj) {
        boolean result = false;

        if (this == obj)
            return true;
        else if(obj instanceof AbstractEntity){
            AbstractEntity other = (AbstractEntity) obj;
            result = Objects.equals(id, other.id);
        }

        return result;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return toStringHelper(this)
                .add("id", id)
                .toString();
    }
}
