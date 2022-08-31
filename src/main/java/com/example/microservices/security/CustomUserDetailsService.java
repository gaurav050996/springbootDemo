package com.example.microservices.security;

import com.example.microservices.users.RolesRepo;
import com.example.microservices.users.UserRepo;
import com.example.microservices.users.UserTable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    UserRepo userRepo;

    @Lazy
    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    RolesRepo rolesRepo;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserTable> user = userRepo.findByName(username);
        if(user.isPresent()) {
            return user.get();
        } else {
            throw new UsernameNotFoundException("Invalid username");
        }
    }
}
