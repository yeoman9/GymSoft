package com.gymsoft.domain.auth;

import java.util.Arrays;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.gymsoft.domain.entity.User;

import lombok.Data;

@Data
public class AuthUser implements UserDetails
{

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private String username;
    private String password;
    private String accountExpired;
    private String accountLocked;
    private String accountEnabled;
    private Long userId;

    public AuthUser( User user )
    {
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
        return Arrays.asList( new SimpleGrantedAuthority( "ROLE_USER" ) );
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
