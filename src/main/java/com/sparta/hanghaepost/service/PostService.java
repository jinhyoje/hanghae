package com.sparta.hanghaepost.service;

import com.sparta.hanghaepost.dto.PostRequestDto;
import com.sparta.hanghaepost.dto.PostResponseDto;
import com.sparta.hanghaepost.dto.ResponseDto;
import com.sparta.hanghaepost.entity.Post;
import com.sparta.hanghaepost.entity.User;
import com.sparta.hanghaepost.entity.UserRoleEnum;
import com.sparta.hanghaepost.jwtUtil.JwtUtil;
import com.sparta.hanghaepost.repository.PostRepository;
import com.sparta.hanghaepost.repository.UserRepository;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;


    @Transactional
    public PostResponseDto createPost(PostRequestDto requestDto, HttpServletRequest request) {
        String token = jwtUtil.resolveToken(request);
        Claims claims;

        if (token != null) {
            if (jwtUtil.validateToken(token)) {
                claims = jwtUtil.getUserInfoFromToken(token);
            } else {
                throw new IllegalArgumentException("Token Error");
            }
            User user = userRepository.findByUsername(claims.getSubject()).orElseThrow(
                    () -> new IllegalArgumentException("사용자가 존재하지 않습니다.")
            );

            Post post = postRepository.saveAndFlush(new Post(requestDto, user.getId(), user.getUsername()));

            return new PostResponseDto(post);
        } else {
            return null;
        }

    }


    @Transactional(readOnly = true)
    public List<PostResponseDto> getPosts() {
        List<Post> getPosts = postRepository.findAllByOrderByCreatedAtDesc();
        ArrayList<PostResponseDto> postResponseDtos = new ArrayList<>();

        if (getPosts.isEmpty()) {
            throw new NullPointerException("게시물이 없습니다.");
        }
        for (Post post : getPosts) {
            postResponseDtos.add(new PostResponseDto(post));
        }
        return postResponseDtos;
    }


    @Transactional(readOnly = true)
    public List<PostResponseDto> getPost(HttpServletRequest request) {
        String token = jwtUtil.resolveToken(request);
        Claims claims;
        if (token != null) {
            if (jwtUtil.validateToken(token)) {
                claims = jwtUtil.getUserInfoFromToken(token);
            } else {
                throw new IllegalArgumentException("Token Error");
            }
            User user = userRepository.findByUsername(claims.getSubject()).orElseThrow(
                    () -> new IllegalArgumentException("사용자가 존재하지 않습니다.")
            );

            UserRoleEnum userRoleEnum = user.getRole();
            System.out.println("role = " + userRoleEnum);

            List<PostResponseDto> list = new ArrayList<>();
            List<Post> postList;


            if (userRoleEnum == userRoleEnum.USER) {
                postList = postRepository.findAllByUsername(user.getUsername());
            } else {
                postList = postRepository.findAllByOrderByCreatedAtDesc();
            }

            for (Post post : postList) {
                list.add((new PostResponseDto(post)));
            }
            return list;
        } else {
            return null;
        }
    }

    @Transactional
    public PostResponseDto updatePost(Long id, PostRequestDto requestDto, HttpServletRequest request) {
        String token = jwtUtil.resolveToken(request);
        Claims claims;

        // 토큰이 있는 경우에만 관심상품 최저가 업데이트 가능
        if (token != null) {
            // Token 검증
            if (jwtUtil.validateToken(token)) {
                // 토큰에서 사용자 정보 가져오기
                claims = jwtUtil.getUserInfoFromToken(token);
            } else {
                throw new IllegalArgumentException("Token Error");
            }
            User user = userRepository.findByUsername(claims.getSubject()).orElseThrow(
                    () -> new IllegalArgumentException("사용자가 존재하지 않습니다.")
            );

            Post post = postRepository.findByIdAndUsername(id, user.getUsername()).orElseThrow(
                    () -> new NullPointerException("게시글이 없습니다.")
            );
            post.update(requestDto);

            return new PostResponseDto(post);
        } else {
            return null;
        }


    }


    public ResponseDto deletePost(Long id, HttpServletRequest request) {

        String token = jwtUtil.resolveToken(request);
        Claims claims;

        // 토큰이 있는 경우에만 관심상품 최저가 업데이트 가능
        if (token != null) {
            // Token 검증
            if (jwtUtil.validateToken(token)) {
                // 토큰에서 사용자 정보 가져오기
                claims = jwtUtil.getUserInfoFromToken(token);
            } else {
                throw new IllegalArgumentException("Token Error");
            }
            User user = userRepository.findByUsername(claims.getSubject()).orElseThrow(
                    () -> new IllegalArgumentException("사용자가 존재하지 않습니다.")
            );
            Post post = postRepository.findByIdAndUsername(id, user.getUsername()).orElseThrow(
                    () -> new NullPointerException("게시글이 없습니다.")
            );
            postRepository.deleteById(id);
            return new ResponseDto("삭제되었습니다.");
        }
        return null;
    }
}
//        }
//        Post post = postRepository.findById(id).orElseThrow(
//                () -> new RuntimeException("게시물이 없습니다.")
//        );
//        return new PostResponseDto(post);


//    @Transactional(readOnly = true)
//    public ResponseDto checkPassword(Long id, PostRequestDto requestDto) {
//        Post post = postRepository.findById(id).orElseThrow(
//                () -> new RuntimeException("게시물이 없습니다.")
//        );
//        if (post.getPassword().equals(requestDto.getPassword())) {
//            return new ResponseDto("확인되었습니다.");
//        }
//        return new ResponseDto("비밀번호를 확인해주세요.");
//    }

//    @Transactional
//    public PostResponseDto updatePost(Long id, PostRequestDto requestDto) {
//        Post post = postRepository.findById(id).orElseThrow(
//                () -> new RuntimeException("게시물이 없습니다.")
//        );
//            post.update(requestDto);
//            return new PostResponseDto(post);
//        }


//    @Transactional
//    public ResponseDto deletePost(Long id, PostRequestDto requestDto) {
//        Post post = postRepository.findById(id).orElseThrow(
//                () -> new RuntimeException("게시물이 없습니다.")
//        );
//        if (post.getPassword().equals(requestDto.getPassword())) {
//            postRepository.deleteById(id);
//            return new ResponseDto("삭제되었습니다.");
//        }
//        return new ResponseDto("비밀번호를 확인해주세요.");
//    }



