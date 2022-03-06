package com.example.letscodeinit.domain;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    USER;

    @Override
    public String getAuthority() {
        return name(); // строковое представление USER , name() берется из класса Enum если что
    }
}
