package com.productservice.userservice.service;

import com.productservice.userservice.model.Token;
import com.productservice.userservice.model.User;
import com.productservice.userservice.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    private UserRepository userRepository;

    UserService(BCryptPasswordEncoder bCryptPasswordEncoder, UserRepository userRepository) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.userRepository = userRepository;
    }

    public User signup(String name, String email, String password){
        User user = new User();
        user.setName(name);
        user.setEmail(email);
        user.setHashedPassword(bCryptPasswordEncoder.encode(password));
        user.setIsEmailVerified(true);
        // now save the user in the database
        User savedUser = userRepository.save(user);
        return savedUser;
    }

    public Token login(String email, String password){
        return null;
    }

    public void logout(String token){

    }

    public User validateToken(String token){
        return null;
    }
}
