package com.sparta.hanghaepost.repository;

import com.sparta.hanghaepost.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    Optional<Comment> findByIdAndUsername(Long id, String username);
}
