package com.the285.nbbang.orm.jpa;

import java.io.Serializable;

public interface EntityId<T> extends Serializable {

    T getId();

    String asString();
    // The asString method returns the ID as a string representation,
    // for use in an URL, for example.
}
