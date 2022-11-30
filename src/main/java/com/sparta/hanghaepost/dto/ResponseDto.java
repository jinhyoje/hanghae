package com.sparta.hanghaepost.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ResponseDto {
    private String msg;

    public ResponseDto(String msg){
        this.msg = msg;
    }
}
