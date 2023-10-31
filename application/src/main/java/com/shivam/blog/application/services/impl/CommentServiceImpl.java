package com.shivam.blog.application.services.impl;

import com.shivam.blog.application.exceptions.ResourceNotFoundException;
import com.shivam.blog.application.models.Comment;
import com.shivam.blog.application.models.Post;
import com.shivam.blog.application.models.User;
import com.shivam.blog.application.paylods.CommentDto;
import com.shivam.blog.application.repositries.CommentRepo;
import com.shivam.blog.application.repositries.PostRepo;
import com.shivam.blog.application.repositries.UserRepo;
import com.shivam.blog.application.services.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private PostRepo postRepo;
    @Autowired
    private CommentRepo commentRepo;
    @Autowired
    private ModelMapper modelMapper;
    @Override
    public CommentDto createComment(CommentDto commentDto, Integer userId, Integer postId) {
        Post post = this.postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post","Id",postId));
        User user  = this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User","Id",userId));
        Comment comment = this.modelMapper.map(commentDto,Comment.class);
        comment.setPost(post);
        comment.setUser(user);
        Comment savedComment=  this.commentRepo.save(comment);
        return this.modelMapper.map(savedComment,CommentDto.class);
    }

    @Override
    public void deleteComment(Integer commentId) {
        Comment comment=  this.commentRepo.findById(commentId).orElseThrow(()->new ResourceNotFoundException("Comment","Id",commentId));
        this.commentRepo.delete(comment);

    }

    @Override
    public List<CommentDto> getAllCommentByUserId(Integer userId) {
        User user = this.userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User","Id",userId));
        List<Comment> comments = this.commentRepo.findByUser(user);
        List<CommentDto>commentDtos = comments.stream().map((comment)->this.modelMapper.map(comment,CommentDto.class)).collect(Collectors.toList());
        return commentDtos;
    }

    @Override
    public List<CommentDto> getAllCommentByPostId(Integer postId) {
        Post post = this.postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post","Id",postId));
        List<Comment> comments = this.commentRepo.findByPost(post);
        List<CommentDto>commentDtos = comments.stream().map((comment)->this.modelMapper.map(comment,CommentDto.class)).collect(Collectors.toList());
        return commentDtos;
    }
}
