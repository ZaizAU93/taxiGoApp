package com.example.taxi.role;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    User, Admin, Driver;

    @Override
    public String getAuthority() {
        return name();
    }
}
