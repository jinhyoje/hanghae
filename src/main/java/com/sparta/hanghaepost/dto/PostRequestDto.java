package com.sparta.hanghaepost.dto;

import com.sparta.hanghaepost.entity.Post;
import lombok.Getter;

@Getter
public class PostRequestDto {
    public String title;
    public String username;
    public String contents;
    public String password;


}
