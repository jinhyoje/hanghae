package com.sparta.hanghaepost.service;

import com.sparta.hanghaepost.dto.CommentRequestDto;
import com.sparta.hanghaepost.dto.CommentResponseDto;
import com.sparta.hanghaepost.dto.ResponseDto;
import com.sparta.hanghaepost.entity.Comment;
import com.sparta.hanghaepost.entity.Post;
import com.sparta.hanghaepost.entity.User;
import com.sparta.hanghaepost.jwtUtil.JwtUtil;
import com.sparta.hanghaepost.repository.CommentRepository;
import com.sparta.hanghaepost.repository.PostRepository;
import com.sparta.hanghaepost.repository.UserRepository;
import com.sparta.hanghaepost.security.UserDetailsImpl;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;

    private final JwtUtil jwtUtil;
    private final UserRepository userRepository;
    private final PostRepository postRepository;

    @Transactional
    public CommentResponseDto save(CommentRequestDto requestDto, Long id, User user) {
//
//        String token = jwtUtil.resolveToken(request);
//        Claims claims;
//
//
//        if (token != null) {
//            if (jwtUtil.validateToken(token)) {
//                claims = jwtUtil.getUserInfoFromToken(token);
//            } else {
//                throw new IllegalArgumentException("Token Error");
//            }
//            User user = userRepository.findByUsername(claims.getSubject()).orElseThrow(
//                    () -> new IllegalArgumentException("사용자가 존재하지 않습니다.")
//            );

            Post post = postRepository.findById(id).orElseThrow(
                    () -> new IllegalArgumentException("게시글이 존재하지 않습니다.")
            );

            System.out.println(requestDto.getContents());

            Comment comment = new Comment(requestDto, post, user.getUsername());
            commentRepository.save(comment);

            return new CommentResponseDto(comment);

        }



    @Transactional
    public CommentResponseDto updateComment(Long post_id, Long comment_id, CommentRequestDto commentRequestDto, User user) {

        Comment findComment = commentRepository.findById(comment_id).orElseThrow(
                ()-> new IllegalArgumentException("작성하신 댓글이 없습니다.")
        ); // 댓글 찾기

        User findUser = userRepository.findByUsername(user.getUsername()).orElseThrow(
                () -> new IllegalArgumentException("작성 댓글 x")
        ); // 유저 찾기


        if(!findComment.getUsername().equals(findUser.getUsername())){
            throw new IllegalArgumentException("잘못된 경로");
        }


        findComment.update(commentRequestDto);


        return new CommentResponseDto(findComment);


    }

    public ResponseDto deleteComment(Long comment_id, User user) {

        Comment findComment = commentRepository.findById(comment_id).orElseThrow(
                ()-> new IllegalArgumentException("작성하신 댓글이 없습니다.")
        ); // 댓글 찾기

        User findUser = userRepository.findByUsername(user.getUsername()).orElseThrow(
                () -> new IllegalArgumentException("작성 댓글 x")
        ); // 유저 찾기


        if(!findComment.getUsername().equals(findUser.getUsername())){
            throw new IllegalArgumentException("잘못된 경로");
        }

        commentRepository.delete(findComment);

        return new ResponseDto("삭제되었습니다", 200);


    }
}



