package com.shivam.blog.application.services;

import com.shivam.blog.application.paylods.PostDto;
import com.shivam.blog.application.paylods.UserDto;

import java.util.List;

public interface PostService {
    PostDto createPost(PostDto postDto, Integer userId, Integer categoryId);
    PostDto updatePost(PostDto postDto, Integer postId);
    PostDto getPostById(Integer postId);
    void deletePost(Integer postId);
    List<PostDto> getAllPost();
    List<PostDto> getAllPostByUserId(Integer userId);
    List<PostDto>getAllPostByCategoryId(Integer categoryId);
    List<PostDto>searchPost(String keyword);
}
