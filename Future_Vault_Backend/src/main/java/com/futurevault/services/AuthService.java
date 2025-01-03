package com.futurevault.services;

import com.futurevault.dtos.AuthResponseDto;
import com.futurevault.dtos.LoginDto;
import com.futurevault.dtos.RegisterDto;

public interface AuthService {
    public Boolean Check_User_Exist(String email);
    public Boolean REGISTER(RegisterDto registerDto);
    public AuthResponseDto LOGIN(LoginDto loginDto);

}
