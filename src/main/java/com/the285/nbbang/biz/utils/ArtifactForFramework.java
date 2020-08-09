package com.the285.nbbang.biz.utils;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * The empty constructor is annotated with @ArtifactForFramework.
 * Create this annotation to indicate that the constructor is solely there for a framework that needs it
 * but its not intended to be used by the application itself.
 */
@Retention(value = RetentionPolicy.SOURCE)
public @interface ArtifactForFramework {
}
