package com.group.Stardust.service;

import com.group.Stardust.models.MyUser;
import com.group.Stardust.repositories.UserRepositories;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MyUserDetailsService implements UserDetailsService {

    private final UserRepositories userRepositories;

    public MyUserDetailsService(UserRepositories userRepositories) {
        this.userRepositories = userRepositories;
    }

    // provides the user info to spring security for authentication and authorization
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<MyUser> userOp = userRepositories.findByUsername(username);

        if(userOp.isPresent()) {
            MyUser user = userOp.get();

            return User.builder()
                    .username(user.getUsername())
                    .password(user.getPassword())
                    .roles(user.getRole())
                    .build();
        } else {
            throw new UsernameNotFoundException("Username is not found");
        }
    }
}
