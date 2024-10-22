package com.productservice.userservice.dto;

import com.productservice.userservice.model.Role;
import com.productservice.userservice.model.User;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
public class UserDTO {
    private String name;
    private String email;
    private ArrayList<Role> role;

    public static UserDTO from(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setName(user.getName());
        userDTO.setEmail(user.getEmail());
        userDTO.setRole(user.getRoles());

        return userDTO;
    }
}
