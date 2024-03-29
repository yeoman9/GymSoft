package com.gymsoft.domain.dto;

import java.util.Date;
import java.util.Set;

import com.gymsoft.domain.entity.Role;

import lombok.Data;

@Data
public class UserDTO
{
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String nickName;
    private String profileImage;
    private String email;
    private Date birthDate;
    private Set<Role> roles;
}
