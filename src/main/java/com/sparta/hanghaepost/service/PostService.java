package com.sparta.hanghaepost.service;

import com.sparta.hanghaepost.dto.PostRequestDto;
import com.sparta.hanghaepost.dto.PostResponseDto;
import com.sparta.hanghaepost.dto.ResponseDto;
import com.sparta.hanghaepost.entity.Post;
import com.sparta.hanghaepost.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    @Transactional
    public Post createPost(PostRequestDto requestDto) {
        Post post = new Post(requestDto);
        postRepository.save(post);
        return post;
    }


    @Transactional(readOnly = true)
    public List<Post> getPosts() {
        return postRepository.findAllByOrderByCreatedAtDesc();
    }


    @Transactional(readOnly = true)
    public PostResponseDto getPost(Long id) {
        Post post = postRepository.findById(id).orElseThrow(
                () -> new RuntimeException("게시물이 없습니다.")
        );
        return new PostResponseDto(post);

    }
    @Transactional(readOnly = true)
    public ResponseDto checkPassword(Long id, PostRequestDto requestDto) {
        Post post = postRepository.findById(id).orElseThrow(
                () -> new RuntimeException("게시물이 없습니다.")
        );
        if (post.getPassword().equals(requestDto.getPassword())) {
            return new ResponseDto("확인되었습니다.");
        }
        return new ResponseDto("비밀번호를 확인해주세요.");
    }

    @Transactional
    public PostResponseDto updatePost(Long id, PostRequestDto requestDto) {
        Post post = postRepository.findById(id).orElseThrow(
                () -> new RuntimeException("게시물이 없습니다.")
        );
            post.update(requestDto);
            return new PostResponseDto(post);
        }





    @Transactional
    public ResponseDto deletePost(Long id, PostRequestDto requestDto) {
        Post post = postRepository.findById(id).orElseThrow(
                () -> new RuntimeException("게시물이 없습니다.")
        );
        if (post.getPassword().equals(requestDto.getPassword())) {
            postRepository.deleteById(id);
            return new ResponseDto("삭제되었습니다.");
        }
        return new ResponseDto("비밀번호를 확인해주세요.");
    }


}
