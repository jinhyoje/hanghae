package com.sparta.hanghaepost.controller;

import com.sparta.hanghaepost.dto.CommentRequestDto;
import com.sparta.hanghaepost.dto.CommentResponseDto;
import com.sparta.hanghaepost.dto.ResponseDto;
import com.sparta.hanghaepost.repository.CommentRepository;
import com.sparta.hanghaepost.repository.UserRepository;
import com.sparta.hanghaepost.security.UserDetailsImpl;
import com.sparta.hanghaepost.service.CommentService;
import com.sparta.hanghaepost.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.servlet.http.HttpServletRequest;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/post/{post_id}/comment")
public class CommentController {

    private final CommentService commentService;
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;

    @PostMapping
    public CommentResponseDto createComment(@RequestBody CommentRequestDto requestDto, @PathVariable Long post_id, @AuthenticationPrincipal UserDetailsImpl userDetails){
       return commentService.save(requestDto, post_id, userDetails.getUser());

    }

    @PutMapping("/{comment_id}")
    public CommentResponseDto updateComment(@PathVariable Long post_id, @PathVariable Long comment_id, @RequestBody CommentRequestDto commentRequestDto, @AuthenticationPrincipal UserDetailsImpl userDetails){
        return commentService.updateComment(post_id, comment_id, commentRequestDto, userDetails.getUser());
    }

    @DeleteMapping("/{comment_id}")
    public ResponseDto deleteComment(@PathVariable Long comment_id, @AuthenticationPrincipal UserDetailsImpl userDetails){
        return commentService.deleteComment(comment_id, userDetails.getUser());
    }




}
