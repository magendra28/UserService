package com.productservice.userservice.model;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
@Entity
public class User extends BaseModel{
    private String name;
    private String email;
    private String hashedPassword;
    @ManyToMany
    private ArrayList<Role> roles;
    private Boolean isEmailVerified;


}
