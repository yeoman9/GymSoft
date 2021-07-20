package com.gymsoft.domain.auth;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gymsoft.domain.entity.Role;
import com.gymsoft.domain.entity.User;

import lombok.Data;

@Data
public class AuthUser implements UserDetails
{

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    @JsonIgnore
    private String username;
    @JsonIgnore
    private String password;
    @JsonIgnore
    private String accountExpired;
    @JsonIgnore
    private String accountLocked;
    @JsonIgnore
    private String accountEnabled;
    @JsonIgnore
    private Long userId;
    @JsonIgnore
    private User user;
    private String token;

    public AuthUser( User user )
    {
    	this.user = user;
        this.username = user.getUsername();
        this.password = user.getPassword();
        this.accountEnabled = user.getAccountEnabled();
        this.accountExpired = user.getAccountExpired();
        this.accountLocked = user.getAccountLocked();
        this.userId = user.getId();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities()
    {
    	Set<Role> roles = user.getUserRoles();
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
         
        for (Role role : roles) {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        }
         
        return authorities;
    }

    @Override
    public String getPassword()
    {
        return password;
    }

    @Override
    public String getUsername()
    {
        return username;
    }

    @Override
    public boolean isAccountNonExpired()
    {
        return "N".equalsIgnoreCase( accountExpired );
    }

    @Override
    public boolean isAccountNonLocked()
    {
        return "N".equalsIgnoreCase( accountLocked );
    }

    @Override
    public boolean isCredentialsNonExpired()
    {
        return true;
    }

    @Override
    public boolean isEnabled()
    {
        return "Y".equalsIgnoreCase( accountEnabled );
    }

}
