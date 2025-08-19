package com.sayan.linkedin.UserService.dtos;

import lombok.Data;

@Data
public class LoginRequestDto {
    private String email;
    private String password;
}
