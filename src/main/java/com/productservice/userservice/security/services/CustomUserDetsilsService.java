package com.productservice.userservice.security.services;

import com.productservice.userservice.model.User;
import com.productservice.userservice.repository.UserRepository;
import com.productservice.userservice.security.models.CustomerUserDetails;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomUserDetsilsService implements UserDetailsService {
    // load the user repo to get the details of user
    private UserRepository userRepository;
    public CustomUserDetsilsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> optionalUser = userRepository.findByEmail(username);

        if(optionalUser.isEmpty()){
            throw new UsernameNotFoundException("User not found"+username+"with this email");
        }

        CustomerUserDetails customerUserDetails = new CustomerUserDetails(optionalUser.get());

        return customerUserDetails;
    }
}
