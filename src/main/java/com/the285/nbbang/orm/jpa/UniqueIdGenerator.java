package com.the285.nbbang.orm.jpa;

public interface UniqueIdGenerator<T> {

    T getNextUniqueId();
}
