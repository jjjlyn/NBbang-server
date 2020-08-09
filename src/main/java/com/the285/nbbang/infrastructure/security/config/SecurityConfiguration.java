package com.the285.nbbang.infrastructure.security.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
// @Component creates an instance to autowire later on.
@Getter
@Setter
@ConfigurationProperties(prefix = "nbbang-security")
// Indicates to the configuration processor that the properties of this class must be made
// available for externalized configuration/
public class SecurityConfiguration {

    private String mobileAppClientId;
    private String mobileAppClientSecret;
}
