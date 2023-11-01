package com.shivam.blog.application.controller;
import com.shivam.blog.application.paylods.ApiResponse;
import com.shivam.blog.application.config.AppConstants;
import com.shivam.blog.application.paylods.PostDto;
import com.shivam.blog.application.paylods.PostResponse;
import com.shivam.blog.application.services.FileService;
import com.shivam.blog.application.services.PostService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@RestController
@RequestMapping("/api")
public class PostController {

    @Autowired
    private PostService postService;

    @Autowired
    private FileService fileService;

    @Value("${project.image}")
    private String path;
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
    private ResponseEntity<PostResponse> getAllpost(@RequestParam(value = "pageNumber",defaultValue = AppConstants.PAGE_NUMBER,required = false)Integer pageNumber,
                                                    @RequestParam(value = "pageSize",defaultValue = AppConstants.PAGE_SIZE,required = false)Integer pageSize,
                                                    @RequestParam(value = "sortBy",defaultValue = AppConstants.SORT_BY,required = false)String sortBy,
                                                    @RequestParam(value ="sortDir",defaultValue = AppConstants.SORT_DIR,required = false)String sortDir){
        PostResponse postResponse = this.postService.getAllPost(pageNumber,pageSize,sortBy,sortDir);
        return new ResponseEntity<>(postResponse,HttpStatus.OK);
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
    @GetMapping("/posts/search/{keyword}")
    private ResponseEntity<List<PostDto>>searchPostByKeyword(@PathVariable String keyword){
        List<PostDto> postDtos = this.postService.searchPost(keyword);
        return new ResponseEntity<>(postDtos,HttpStatus.OK);
    }
    @PostMapping("/post/image/upload/{postId}")
    private ResponseEntity<PostDto> uploadPostImage(@RequestParam("image")MultipartFile image, @PathVariable Integer postId) throws IOException {
        this.fileService.uploadImage(path,image);
        PostDto postDto =this.postService.getPostById(postId);
        String fileName = this.fileService.uploadImage(path,image);
        postDto.setImageName(fileName);
        PostDto updatedPost =this.postService.updatePost(postDto,postId);
        return new ResponseEntity<PostDto>(updatedPost,HttpStatus.OK);
    }@GetMapping(value = "/post/image/{imageName}",produces = MediaType.IMAGE_JPEG_VALUE)
    public void downloadImage(@PathVariable String imageName, HttpServletResponse response) throws IOException{
        InputStream resource =this.fileService.getResource(path,imageName);
        response.setContentType(MediaType.IMAGE_JPEG_VALUE);
        StreamUtils.copy(resource,response.getOutputStream());
    }
}
