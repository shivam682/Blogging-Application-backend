package com.shivam.blog.application.services.impl;

import com.shivam.blog.application.exceptions.ResourceNotFoundException;
import com.shivam.blog.application.models.Category;
import com.shivam.blog.application.models.Post;
import com.shivam.blog.application.models.User;
import com.shivam.blog.application.paylods.PostDto;
import com.shivam.blog.application.repositries.CategoryRepo;
import com.shivam.blog.application.repositries.PostRepo;
import com.shivam.blog.application.repositries.UserRepo;
import com.shivam.blog.application.services.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepo postRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private CategoryRepo categoryRepo;



    @Override
    public PostDto createPost(PostDto postDto, Integer userId, Integer categoryId) {
        User user = this.userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User","Id",userId));
        Category category= this.categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category","Id",categoryId));
        Post post = this.modelMapper.map(postDto,Post.class);
        post.setAddedDate(new Date());
        post.setCategory(category);
        post.setUser(user);
        post.setImageName("default.png");
        this.postRepo.save(post);
        return this.modelMapper.map(post,PostDto.class);
    }

    @Override
    public PostDto updatePost(PostDto postDto, Integer postId) {
        return null;
    }

    @Override
    public PostDto getPostById(Integer postId) {
        return null;
    }

    @Override
    public void deletePost(Integer postId) {

    }

    @Override
    public List<PostDto> getAllPost() {
        return null;
    }

    @Override
    public List<PostDto> getAllPostByUserId(Integer userId) {
        User user = this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User","Id",userId));
        List<Post> posts= this.postRepo.findByUser(user);
        List<PostDto> postDtos = posts.stream().map((post)->this.modelMapper.map(post,PostDto.class)).collect(Collectors.toList());
        return postDtos;
    }

    @Override
    public List<PostDto> getAllPostByCategoryId(Integer categoryId) {

        Category category = this.categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category","Id",categoryId));
        List<Post> posts= this.postRepo.findByCategory(category);
        List<PostDto> postDtos = posts.stream().map((post)->this.modelMapper.map(post,PostDto.class)).collect(Collectors.toList());
        return postDtos;
    }

    @Override
    public List<PostDto> searchPost(String keyword) {
        return null;
    }
}
