package com.the285.nbbang.biz.dto.user;

import com.the285.nbbang.orm.jpa.InMemoryUniqueIdGenerator;
import com.the285.nbbang.orm.jpa.UniqueIdGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.swing.text.html.Option;
import java.util.HashSet;
import java.util.Locale;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class UserRepositoryTest {

    @Autowired
    private UserRepository repository;

    @Test
    public void testStoreUser(){
        HashSet<UserRole> roles = new HashSet<>();
        roles.add(UserRole.GENERAL);
        User user = repository.save(new User(repository.nextId(),
                "prize1142@gmail.com",
                "my-secret-pwd",
                roles));

        assertNotNull(user);
        assertEquals(repository.count(), 1L);
    }

    @Test
    public void testFindByEmail(){
        User user = Users.newRandomAdmin();
        repository.save(user);
        Optional<User> optional = repository.findByEmailIgnoreCase(user.getEmail());

        assertThat(optional).isNotEmpty().contains(user);
    }

    @Test
    public void testFindByEmailIgnoringCase(){
        User user = Users.newRandomAdmin();
        repository.save(user);
        Optional<User> optional = repository.findByEmailIgnoreCase(user.getEmail().toUpperCase(Locale.US));

        assertThat(optional).isNotEmpty().contains(user);
    }

    @Test
    public void testFindByEmail_unknownEmail(){
        User user = Users.newRandomAdmin();
        repository.save(user);
        Optional<User> optional = repository.findByEmailIgnoreCase("will.not@find.me");

        assertThat(optional).isEmpty();
    }

    @TestConfiguration
    static class TestConfig {
        @Bean
        public UniqueIdGenerator<UUID> generator(){
            return new InMemoryUniqueIdGenerator();
        }
    }
}