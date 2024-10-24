package com.productservice.userservice.security.models;

import com.productservice.userservice.model.Role;
import org.springframework.security.core.GrantedAuthority;

public class CustomerGrantedAuthority implements GrantedAuthority {
    private String authority;

    public CustomerGrantedAuthority(Role role) {
        this.authority = role.getValue();
    }
    @Override
    public String getAuthority() {
        return authority;
    }
}
