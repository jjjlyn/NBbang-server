package com.the285.nbbang.orm.jpa;

import java.io.Serializable;

public interface EntityId<T> extends Serializable {

    T getId();

    String asString();
    // The asString method returns the ID as a string representation,
    // for use in an URL, for example.
    // You are not using toString because that is usually for debugging purposes
    // while you will need to use this as part of your application logic.
}
