package com.the285.nbbang.infrastructure.security.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
@ConfigurationProperties(prefix = "nbbang-security")
public class SecurityConfiguration {
    private String mobileAppClientId;
    private String mobileAppClientSecret;
}
