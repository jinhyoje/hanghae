package com.sparta.hanghaepost.entity;

import com.sparta.hanghaepost.dto.CommentRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Objects;

@Entity
@NoArgsConstructor
@Getter
@Table(name = "comments")
public class Comment extends Timestamped{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(nullable = false)
    private String contents;


    @Column(nullable = false)
    private String username;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;


    public Comment(CommentRequestDto requestDto, Post post, String username) {
        this.contents = requestDto.getContents();
 //       this.username = requestDto.getUsername();
        this.post = post;
        this.username = username;


    }

    public void update(CommentRequestDto commentRequestDto) {
        this.contents = commentRequestDto.getContents();
    }




}
