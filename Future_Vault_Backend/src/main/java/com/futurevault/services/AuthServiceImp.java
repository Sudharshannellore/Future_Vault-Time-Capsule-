package com.futurevault.services;

import com.futurevault.dtos.CapsuleUserResponseDto;
import com.futurevault.dtos.LoginDto;
import com.futurevault.dtos.RegisterDto;
import com.futurevault.dtos.AuthResponseDto;
import com.futurevault.entites.Users;
import com.futurevault.repositories.UsersRepository;
import com.futurevault.securityservice.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class AuthServiceImp implements AuthService {

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Override
    public Boolean Check_User_Exist(String email) {
        return usersRepository.existsByEmail(email);
    }

    @Override
    public Boolean REGISTER(RegisterDto registerDto) {

        if (Check_User_Exist(registerDto.getEmail())){
            return false;
        }
        Users users = new Users();
        users.setUsername(registerDto.getUsername());
        users.setPassword(passwordEncoder.encode(registerDto.getPassword()));
        users.setEmail(registerDto.getEmail());
        usersRepository.save(users);
        return true;
    }

    @Override
    public AuthResponseDto LOGIN(LoginDto loginDto) {

        User user = (User) customUserDetailsService.loadUserByUsername(loginDto.getEmail());

        if (passwordEncoder.matches(loginDto.getPassword(), user.getPassword())){

            Users users = usersRepository.findByEmail(loginDto.getEmail()).get();

            AuthResponseDto userResponseDto = new AuthResponseDto();

            userResponseDto.setUserid(users.getUserid());
            userResponseDto.setUsername(users.getUsername());
            userResponseDto.setUseremail(users.getEmail());
            userResponseDto.setCapsuleUserResponseDto(users.getCapsules()
                    .stream().map(capsule -> new CapsuleUserResponseDto(
                            capsule.getCapsuleid(),
                            capsule.getTitle(),
                            capsule.getUnlockdatetime().toString()
                    ))
                    .collect(Collectors.toList())
            );
            return userResponseDto;
        }
        else {
            return null;
        }
    }
}
