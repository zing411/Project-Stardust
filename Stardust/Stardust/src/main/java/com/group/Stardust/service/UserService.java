package com.group.Stardust.service;

import com.group.Stardust.models.MyUser;
import com.group.Stardust.repositories.UserRepositories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepositories userRepositories;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepositories userRepositories, PasswordEncoder passwordEncoder) {
        this.userRepositories = userRepositories;
        this.passwordEncoder = passwordEncoder;
    }

    // save the user into a database
     // 0 - user already exists, 1 - user already saved
    public int saveUser(MyUser user) {
        if(userRepositories.findByUsername(user.getUsername()).isPresent()){
            return 0;
        }

        // encrypting password
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        // save the user
        userRepositories.save(user);
        return 1;
    }
}
