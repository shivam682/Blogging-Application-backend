package com.shivam.blog.application.services;

import com.shivam.blog.application.paylods.UserDto;

import java.util.List;

public interface UserService {

    UserDto createUser(UserDto user);
    UserDto updateUser(UserDto user, Integer userId);
    UserDto getUserById(Integer user);
    void deleteUser(Integer userId);
    List<UserDto> getAllUser();
}
