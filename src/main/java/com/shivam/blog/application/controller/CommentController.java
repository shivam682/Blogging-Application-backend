package com.shivam.blog.application.controller;

import com.shivam.blog.application.paylods.ApiResponse;
import com.shivam.blog.application.paylods.CommentDto;
import com.shivam.blog.application.services.CommentService;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CommentController {
    @Autowired
    private CommentService commentService;

    @PostMapping("/post/{postId}/user/{userId}/comments")
    private ResponseEntity<CommentDto>createComment(@RequestBody CommentDto commentDto, @PathVariable Integer postId,@PathVariable Integer userId){
        CommentDto createdCommentDto =this.commentService.createComment(commentDto, postId, userId);
        return new ResponseEntity<>(createdCommentDto, HttpStatus.CREATED);
    }
    @DeleteMapping("/{commentId}")
    private ResponseEntity<ApiResponse>deleteComment(@PathVariable Integer commentId){
        this.commentService.deleteComment(commentId);
        return new ResponseEntity<>(new ApiResponse("Comment Deleted Succefully",true),HttpStatus.OK);
    }
    @GetMapping("/user/{userId}/comments")
    private ResponseEntity<List<CommentDto>>getAllCommentByUserId(@PathVariable Integer userId){
        List<CommentDto> commentDtos = this.commentService.getAllCommentByUserId(userId);
        return new ResponseEntity<>(commentDtos,HttpStatus.OK);
    }
    @GetMapping("/post/{postId}/comments")
    private ResponseEntity<List<CommentDto>>getAllCommentByPostId(@PathVariable Integer postId){
        List<CommentDto> commentDtos = this.commentService.getAllCommentByPostId(postId);
        return new ResponseEntity<>(commentDtos,HttpStatus.OK);
    }
}
