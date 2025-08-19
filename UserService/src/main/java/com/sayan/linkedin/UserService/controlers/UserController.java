package com.sayan.linkedin.UserService.controlers;

import com.sayan.linkedin.UserService.dtos.LoginRequestDto;
import com.sayan.linkedin.UserService.dtos.SignUpRequestDto;
import com.sayan.linkedin.UserService.dtos.UserDto;
import com.sayan.linkedin.UserService.services.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class UserController {

    private final AuthService authService;

    @PostMapping("/signUp")
    public ResponseEntity<UserDto> signUp(@RequestBody SignUpRequestDto signUpRequestDto){
        UserDto userDto = authService.signUp(signUpRequestDto);
        return new ResponseEntity<>(userDto, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequestDto loginRequestDto){
        String token = authService.login(loginRequestDto);
        return ResponseEntity.ok(token);
    }
}
