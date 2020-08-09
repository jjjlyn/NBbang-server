package com.the285.nbbang;

import com.the285.nbbang.orm.jpa.InMemoryUniqueIdGenerator;
import com.the285.nbbang.orm.jpa.UniqueIdGenerator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;

import java.util.UUID;

@SpringBootApplication
public class NbbangApplication {

	public static void main(String[] args) {
		SpringApplication.run(NbbangApplication.class, args);
	}

	@Bean
	public UniqueIdGenerator<UUID> uniqueIdGenerator() {
		return new InMemoryUniqueIdGenerator();
	}

	@Bean
	// A PasswordEncoder for encoding a user-supplied password into something that can be safely stored in the database
	public PasswordEncoder passwordEncoder(){
		return new BCryptPasswordEncoder();
	}

	@Bean
	// A TokenStore for storing generated access and refresh tokens
	public TokenStore tokenStore(){
		return new InMemoryTokenStore();
	}
}
