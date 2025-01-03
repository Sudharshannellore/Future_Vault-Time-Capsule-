package com.futurevault.controllers;

import com.futurevault.dtos.AuthResponseDto;
import com.futurevault.dtos.LoginDto;
import com.futurevault.dtos.RegisterDto;
import com.futurevault.services.AuthServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthServiceImp authServiceImp;

    @PostMapping("/register")
    public ResponseEntity<String> REGISTER_USER(@RequestBody RegisterDto registerDto){

      Boolean isExist = authServiceImp.Check_User_Exist(registerDto.getEmail());
      if (isExist){
          return ResponseEntity.status(HttpStatus.CONFLICT).body(registerDto.getEmail()+" : User already exist..!");
      }
      Boolean isRegister = authServiceImp.REGISTER(registerDto);
      if (isRegister){
          return ResponseEntity.ok(registerDto.getEmail()+" : Successfully Registered..!");
      }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Please Check Your Details..!");
    }

    @PostMapping("/login")
    public ResponseEntity<?> LOGIN_USER(@RequestBody LoginDto loginDto){

        Boolean isExist = authServiceImp.Check_User_Exist(loginDto.getEmail());

        if (!isExist){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(loginDto.getEmail()+" : User Not Found..!");
        }

        AuthResponseDto response = authServiceImp.LOGIN(loginDto);

        if (response != null){
            return  ResponseEntity.ok(response);
        }
        else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(loginDto.getPassword()+" : Incorrect Password..! ");
        }

    }

}
