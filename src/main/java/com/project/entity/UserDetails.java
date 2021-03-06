package com.project.entity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class UserDetails implements org.springframework.security.core.userdetails.UserDetails {

    private String username;
    private String password;
    private boolean active;
    private List<GrantedAuthority> authorities;
    public UserDetails() {
    }

    public UserDetails(User user) {
        this.username = user.getUsername();
        this.password=user.getPassword();
        this.active= user.getUserStatus() == UserStatus.ACTIVE;
        this.authorities=new ArrayList<>();
        this.authorities.add(new SimpleGrantedAuthority(user.getUserType().toString()));
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities()
    {
        return this.authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return this.active;
    }
}
