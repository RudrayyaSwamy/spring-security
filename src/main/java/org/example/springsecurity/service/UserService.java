package org.example.springsecurity.service;

import org.example.springsecurity.Entity.UserEntity;
import org.example.springsecurity.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;


    public Object findByName(String username) {
        return ResponseEntity.ok(userRepository.findByName(username));
    }

    public Object findAll() {
        return ResponseEntity.ok(userRepository.findAll());
    }

    public UserEntity save(UserEntity userEntity) {
        return userRepository.save(userEntity);
    }
}
