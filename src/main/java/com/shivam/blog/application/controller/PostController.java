package com.shivam.blog.application.controller;
import com.shivam.blog.application.paylods.ApiResponse;
import com.shivam.blog.application.paylods.PostDto;
import com.shivam.blog.application.services.PostService;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class PostController {

    @Autowired
    private PostService postService;
    //create
    @PostMapping("/user/{userId}/category/{categoryId}/posts")
    private ResponseEntity<PostDto>createPost(@RequestBody PostDto postDto, @PathVariable Integer userId, @PathVariable Integer categoryId){
         PostDto createdPost =  this.postService.createPost(postDto,userId,categoryId);
         return new ResponseEntity<>(createdPost, HttpStatus.CREATED);
    }

    @GetMapping("/user/{userId}/posts")
    private ResponseEntity<List<PostDto>>getAllPostByUserId(@PathVariable Integer userId){
        List<PostDto> postDtos = this.postService.getAllPostByUserId(userId);
        return new ResponseEntity<>(postDtos,HttpStatus.OK);
    }
    @GetMapping("/category/{categoryId}/posts")
    private ResponseEntity<List<PostDto>>getAllPostByCategoryId(@PathVariable Integer categoryId){
        List<PostDto> postDtos = this.postService.getAllPostByCategoryId(categoryId);
        return new ResponseEntity<>(postDtos,HttpStatus.OK);
    }
    @GetMapping("/posts")
    private ResponseEntity<List<PostDto>>getAllposts(){
        List<PostDto> postDtos = this.postService.getAllPost();
        return new ResponseEntity<>(postDtos,HttpStatus.OK);
    }
    @GetMapping("/posts/{postId}")
    private ResponseEntity<PostDto>getPostById(@PathVariable Integer postId){
        PostDto postDto =this.postService.getPostById(postId);
        return new ResponseEntity<>(postDto,HttpStatus.OK);
    }
    @DeleteMapping("/posts/{postId}")
    private ResponseEntity<ApiResponse>deletePostById(@PathVariable Integer postId){
        this.postService.deletePost(postId);
        return new ResponseEntity<>(new ApiResponse("Post deleted ",false),HttpStatus.OK);
    }
    @PutMapping("/posts/{postId}")
    private ResponseEntity<PostDto>updatePostById(@RequestBody PostDto postDto,@PathVariable Integer postId){
        PostDto updatedPostDto =this.postService.updatePost(postDto,
                postId);
        return new ResponseEntity<>(updatedPostDto,HttpStatus.OK);
    }
}
