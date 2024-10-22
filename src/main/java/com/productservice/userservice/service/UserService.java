package com.productservice.userservice.service;

import com.productservice.userservice.exceptions.UserNotFoundException;
import com.productservice.userservice.model.Token;
import com.productservice.userservice.model.User;
import com.productservice.userservice.repository.TokenRepository;
import com.productservice.userservice.repository.UserRepository;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.Optional;

@Service
public class UserService {
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    private UserRepository userRepository;
    private TokenRepository tokenRepository;

    UserService(BCryptPasswordEncoder bCryptPasswordEncoder, UserRepository userRepository, TokenRepository tokenRepository) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.userRepository = userRepository;
        this.tokenRepository = tokenRepository;
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
        // 1. first we need to search in db that this email is there or not
        System.out.println("the emailand password"+email +" "+password);
        Optional<User> optionalUser = userRepository.findByEmail(email);
        System.out.println("user is present in the db or not"+optionalUser.isPresent());

        if(optionalUser.isEmpty()){
            throw new UserNotFoundException("User with "+email+" not found");
        }
        User user = optionalUser.get();
        System.out.println("user data  in the db"+user);
        if(!bCryptPasswordEncoder.matches(password,user.getHashedPassword())){
            // if the password is not matching throw some exception
//            throw new BadCredentialsException("Bad credentials");
            return null;
        }
        // if password matches ,generate a token
        Token token = generateRandomToken(user);
        Token savedToken = tokenRepository.save(token);
        return savedToken;
    }

    // generate a random token for now
    private Token generateRandomToken(User user){
        LocalDate currentDate = LocalDate.now();
        LocalDate thirtyDaysLater = currentDate.plusDays(30);

        Date expiryDate = Date.from(thirtyDaysLater.atStartOfDay(ZoneId.systemDefault()).toInstant());
        Token token = new Token();
        token.setExpiryAt(expiryDate);
        token.setTokenVal(RandomStringUtils.randomAlphanumeric(128));
        token.setUser(user);
        return token;
    }

    public void logout(String tokenValue){
        Optional<Token> optionalUser = tokenRepository.findByTokenValAndDeleted(tokenValue,false);
        if(optionalUser.isEmpty()){
            return;
        }
        Token token = optionalUser.get();
        token.setDeleted(true);
        tokenRepository.save(token);
    }

    public User validateToken(String token){
        Optional<Token> optionalToken = tokenRepository.findByTokenValAndDeletedAndExpiryAtGreaterThan(token,false,new Date());

        if(optionalToken.isEmpty()){
            return null;
        }

        return optionalToken.get().getUser();
    }
}
