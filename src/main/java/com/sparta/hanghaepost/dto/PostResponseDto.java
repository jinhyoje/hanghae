package com.sparta.hanghaepost.dto;

import com.sparta.hanghaepost.entity.Post;

public class PostResponseDto {

    public String title;
    public String username;
    public String contents;
    public String password;

    public PostResponseDto(Post post) {
        this.title = post.getTitle();
        this.username = post.getUsername();
        this.contents = post.getContents();
        this.password = post.getPassword();
    }
}
