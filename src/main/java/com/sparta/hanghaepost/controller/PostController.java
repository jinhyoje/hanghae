package com.sparta.hanghaepost.controller;

import com.sparta.hanghaepost.dto.PostRequestDto;
import com.sparta.hanghaepost.dto.PostResponseDto;
import com.sparta.hanghaepost.dto.ResponseDto;
import com.sparta.hanghaepost.entity.Post;
import com.sparta.hanghaepost.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class PostController {

    private final PostService postService;

    @PostMapping("/api/post") //게시글 작성
    public Post creatPost(@RequestBody PostRequestDto requestDto){

        return postService.createPost(requestDto);

    }

    @GetMapping("/api/posts") // 전체 게시글 보기
    public List<Post> getPosts(){
        return postService.getPosts();
    }

    @GetMapping("/api/post/{id}") // 선택 게시글 보기
    public PostResponseDto getPost(@PathVariable Long id){
        return postService.getPost(id);
    }

    @PostMapping("/api/post/{id}") // 비밀번호 확인
    public ResponseDto checkPassword(@PathVariable Long id, @RequestBody PostRequestDto requestDto){
        return postService.checkPassword(id, requestDto);
    }

    @PutMapping("/api/post/{id}") //선택 게시글 수정
    public PostResponseDto updatePost(@PathVariable Long id, @RequestBody PostRequestDto requestDto){
        return postService.updatePost(id, requestDto);
    }

    @DeleteMapping("/api/post/{id}") // 선택 게시글 삭제
    public ResponseDto deletePost(@PathVariable Long id, @RequestBody PostRequestDto requestDto){
        return postService.deletePost(id, requestDto);
    }
}