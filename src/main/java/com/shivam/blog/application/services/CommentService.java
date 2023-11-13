package com.shivam.blog.application.services;

import com.shivam.blog.application.paylods.CommentDto;
import jakarta.persistence.criteria.CriteriaBuilder;

import java.util.List;

public interface CommentService {

    CommentDto createComment(CommentDto commentDto, Integer userId, Integer postId);
    void deleteComment(Integer commentId);

    List<CommentDto> getAllCommentByUserId(Integer userId);
    List<CommentDto> getAllCommentByPostId(Integer postId);
}
