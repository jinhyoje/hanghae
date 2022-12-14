package com.sparta.hanghaepost.dto;

import com.sparta.hanghaepost.entity.Post;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
public class PostResponseDto {

    public String title;
    public String contents;
    public String username;
    public LocalDateTime createdAt;
    public LocalDateTime modifiedAt;

    public List<CommentResponseDto> commentResponseDtos = new ArrayList<>();


    public PostResponseDto(Post post) {
        this.title = post.getTitle();
        this.contents = post.getContents();
        this.username = post.getUsername();
        this.createdAt = post.getCreatedAt();
        this.modifiedAt = post.getModifiedAt();

    }

    public PostResponseDto(Post post, List<CommentResponseDto> commentResponseDtos) {
        this.title = post.getTitle();
        this.contents = post.getContents();
        this.username = post.getUsername();
        this.createdAt = post.getCreatedAt();
        this.modifiedAt = post.getModifiedAt();
        this.commentResponseDtos = commentResponseDtos;

    }
}
