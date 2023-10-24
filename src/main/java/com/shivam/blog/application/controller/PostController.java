package com.shivam.blog.application.controller;
import com.shivam.blog.application.paylods.PostDto;
import com.shivam.blog.application.services.PostService;
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
}
