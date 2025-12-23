package com.example.demo4.SecurityApp.dto;


import lombok.*;

@Data
@Setter
@Getter
@RequiredArgsConstructor
@AllArgsConstructor
public class loginDto {
    private String email;
    private String password;

}
