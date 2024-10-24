package com.productservice.userservice.security.models;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.productservice.userservice.model.Role;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

@JsonDeserialize
public class CustomerGrantedAuthority implements GrantedAuthority {
    private String authority;

    public CustomerGrantedAuthority() {

    }

    public CustomerGrantedAuthority(Role role) {
        this.authority = role.getValue();
    }
    @Override
    public String getAuthority() {
        return authority;
    }
}
