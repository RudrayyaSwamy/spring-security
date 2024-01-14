package org.example.springsecurity.controller;

import org.example.springsecurity.Entity.Product;
import org.example.springsecurity.Entity.UserEntity;
import org.example.springsecurity.repository.UserRepository;
import org.example.springsecurity.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {


    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ProductService productService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/")
    public String goH0me(){
        return "Thisn is publickly accesible withing needing authentication ";
    }

    @PostMapping("/user/save")
    public ResponseEntity<Object> saveUSer(@RequestBody UserEntity userEntity){
        userEntity.setPassword(passwordEncoder.encode(userEntity.getPassword()));
        UserEntity result = userRepository.save(userEntity);
        if (result.getId() > 0){
            return ResponseEntity.ok("USer Was Saved");
        }
        return ResponseEntity.status(404).body("Error, USer Not Saved");
    }

    @GetMapping("/product/all")
    public ResponseEntity<Object> getAllProducts(){
        return ResponseEntity.ok(productService.getProducts());
    }

    @GetMapping("/users/all")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Object> getAllUSers(){
        return ResponseEntity.ok(userRepository.findAll());
    }

    @GetMapping("/users/single")
    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('USER')")
    public ResponseEntity<Object> getMyDetails(){
        return ResponseEntity.ok(userRepository.findByName(getLoggedInUserDetails().getUsername()));
    }

    public UserDetails getLoggedInUserDetails(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication != null && authentication.getPrincipal() instanceof UserDetails){
            return (UserDetails) authentication.getPrincipal();
        }
        return null;
    }
}
