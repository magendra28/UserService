package com.productservice.userservice.controller;

import com.productservice.userservice.dto.LoginDTO;
import com.productservice.userservice.dto.LogoutDTO;
import com.productservice.userservice.dto.SignUpDTO;
import com.productservice.userservice.dto.UserDTO;
import com.productservice.userservice.model.Token;
import com.productservice.userservice.model.User;
import com.productservice.userservice.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {
    // inject the user service class via constructor injection
    private UserService userService;
    UserController(UserService userService) {
        this.userService = userService;
    }


    @PostMapping("/signup")
    public UserDTO signUp(@RequestBody SignUpDTO signUpDTO){
        User user = userService.signup(
                signUpDTO.getName(),
                signUpDTO.getEmail(),
                signUpDTO.getPassword()
        );

        return UserDTO.from(user);
    }

    @PostMapping("/login")
    public Token logIn(@RequestBody LoginDTO loginDTO){
        return null;
    }

    @PostMapping("/logout")
    public ResponseEntity<Void> logOut(@RequestBody LogoutDTO logoutDTO){
        return null;
    }

    @PostMapping("/validate/{token}")
    public UserDTO validateToken(@PathVariable String token){
        return null;
    }
}