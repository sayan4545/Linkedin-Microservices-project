package com.sayan.linkedin.UserService.services;

import com.sayan.linkedin.UserService.dtos.LoginRequestDto;
import com.sayan.linkedin.UserService.dtos.SignUpRequestDto;
import com.sayan.linkedin.UserService.dtos.UserDto;
import com.sayan.linkedin.UserService.entities.UserEntity;
import com.sayan.linkedin.UserService.exceptions.BadCredentialsExceptions;
import com.sayan.linkedin.UserService.repositories.UserRepository;
import com.sayan.linkedin.UserService.utils.Bcrypt;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final JwtService jwtService;

    public UserDto signUp(SignUpRequestDto signUpRequestDto) {
        log.info("Signup user with email : {}",signUpRequestDto.getEmail());
        UserEntity userEntity = modelMapper.map(signUpRequestDto,UserEntity.class);
        userEntity.setPassword(Bcrypt.hash(signUpRequestDto.getPassword()));
        //check if the user already exists or not
        boolean isExists = userRepository.existsByEmail(userEntity.getEmail());
        if(isExists) throw new BadCredentialsExceptions("user alreadty exists with email : "+ userEntity.getEmail());
        userEntity = userRepository.save(userEntity);

        return modelMapper.map(userEntity, UserDto.class);
    }

    public String login(LoginRequestDto loginRequestDto){
        log.info("Logging in with user with email {}",loginRequestDto.getEmail());
        UserEntity user = userRepository.findByEmail(loginRequestDto.getEmail())
                .orElseThrow(()-> new BadCredentialsExceptions("either username or password is incorrect"));
        // check if password matches or not
        boolean isMatchedPassword = Bcrypt.check(loginRequestDto.getPassword(),user.getPassword());
        if(!isMatchedPassword) throw new BadCredentialsExceptions("either username or password is incorrect");

        return jwtService.generateAccessToken(user);

    }
}
