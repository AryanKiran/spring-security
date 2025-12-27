package com.example.demo4.SecurityApp.dto;


import com.example.demo4.SecurityApp.enums.Role;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Data
@Getter
@Setter
public class SignUpDto {
    private String email;
    private String password;
    private String name;
    private Set<Role> roles;
}
