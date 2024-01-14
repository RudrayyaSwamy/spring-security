package org.example.springsecurity.config;

import org.example.springsecurity.Entity.UserEntity;
import org.example.springsecurity.model.User;
import org.example.springsecurity.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

public class UserInfoUserDetailsService implements UserDetailsService {


    @Autowired
    private UserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserEntity> userEntity = repository.findByName(username);
        return userEntity.map(User::new)
                .orElseThrow(() -> new UsernameNotFoundException("user not found " + username));
    }
}
