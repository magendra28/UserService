package com.productservice.userservice.dto;

import com.productservice.userservice.model.Role;
import com.productservice.userservice.model.User;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Getter
@Setter
public class UserDTO {
    private String name;
    private String email;
    private List<Role> role;

    public static UserDTO from(User user) {
        if(user == null){
            return null;
        }
        UserDTO userDTO = new UserDTO();
        userDTO.setName(user.getName());
        userDTO.setEmail(user.getEmail());
        userDTO.setRole(user.getRoles());

        return userDTO;
    }
}
