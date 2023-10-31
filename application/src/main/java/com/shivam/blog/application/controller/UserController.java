package com.shivam.blog.application.controller;


import com.shivam.blog.application.paylods.ApiResponse;
import com.shivam.blog.application.paylods.UserDto;
import com.shivam.blog.application.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {


    @Autowired
    private UserService userService;

    @PostMapping("/")
    private ResponseEntity<UserDto> createuser(@Valid @RequestBody UserDto userDto){
        UserDto createdUserDto = this.userService.createUser(userDto);
        return new ResponseEntity<>(createdUserDto, HttpStatus.CREATED);
    }
    @PutMapping("/{userId}")
    private ResponseEntity<UserDto> updateUser(@Valid @RequestBody UserDto userDto, @PathVariable Integer userId){
        UserDto updatedUser= this.userService.updateUser(userDto,userId);
        return new ResponseEntity<>(updatedUser,HttpStatus.OK);
    }

    @DeleteMapping("/{userId}")
    private ResponseEntity<ApiResponse> deleteUser (@PathVariable Integer userId){
         this.userService.deleteUser(userId);
         return new ResponseEntity<>(new ApiResponse("User Deleted", true),HttpStatus.OK);
    }

    @GetMapping("/{userId}")
    private ResponseEntity<UserDto> getUser(@PathVariable Integer userId){
        UserDto userDto = this.userService.getUserById(userId);
        return new ResponseEntity<>(userDto,HttpStatus.OK);
    }
    @GetMapping("/")
    private ResponseEntity<List<UserDto>> getAllUser(){
        List<UserDto> userDtos = this.userService.getAllUser();
        return new ResponseEntity<>(userDtos,HttpStatus.OK);
    }
}
