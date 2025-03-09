package com.recycle.domain.comment.repository;

import com.recycle.domain.comment.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    Optional<Comment> findByIdAndIsDeletedFalse(Long commentId);
}
