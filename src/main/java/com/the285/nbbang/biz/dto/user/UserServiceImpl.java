package com.the285.nbbang.biz.dto.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository mUserRepository;
    private final PasswordEncoder mPasswordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder){
        this.mUserRepository = userRepository;
        this.mPasswordEncoder = passwordEncoder;
    }

    @Override
    public User createAdmin(String email, String password) {
        User user = User.createAdmin(mUserRepository.nextId(), email, mPasswordEncoder.encode(password));

        return mUserRepository.save(user);
    }
}
