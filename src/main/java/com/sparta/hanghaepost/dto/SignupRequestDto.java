package com.sparta.hanghaepost.dto;

import lombok.Getter;
import lombok.Setter;

 @Setter
 @Getter
public class SignupRequestDto {
    private String username;
    private String password;
    private boolean admin ;
    private String adminToken = "";


 }
