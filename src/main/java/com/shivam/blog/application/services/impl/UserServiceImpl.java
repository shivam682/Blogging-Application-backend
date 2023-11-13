package com.shivam.blog.application.services.impl;

import com.shivam.blog.application.config.AppConstants;
import com.shivam.blog.application.exceptions.ResourceNotFoundException;
import com.shivam.blog.application.models.Role;
import com.shivam.blog.application.models.User;
import com.shivam.blog.application.paylods.UserDto;
import com.shivam.blog.application.repositries.RoleRepo;
import com.shivam.blog.application.repositries.UserRepo;
import com.shivam.blog.application.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {


    @Autowired
    private UserRepo userRepo;
    @Autowired
    private RoleRepo roleRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDto registerUser(UserDto userDto) {
        User user= this.modelMapper.map(userDto,User.class);
        user.setPassword(this.passwordEncoder.encode(user.getPassword()));
        Role role= this.roleRepo.findById(AppConstants.NORMAL_USER).get();
        user.getRoles().add(role);
        User savedUser=this.userRepo.save(user);
        return this.modelMapper.map(savedUser,UserDto.class);

    }

    @Override
    public UserDto createUser(UserDto userDto) {
        User user = this.userDtotoUser(userDto);
        this.userRepo.save(user);
        return this.userToDto(user);
    }

    @Override
    public UserDto updateUser(UserDto userDto, Integer userId) {
        User user =this.userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User","Id",userId));
        user.setAbout(userDto.getAbout());
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        User updatedUser=this.userRepo.save(user);
        return this.userToDto(updatedUser);
    }

    @Override
    public UserDto getUserById(Integer userId) {
        User user =this.userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User","Id", userId));
        return this.userToDto(user);
    }

    @Override
    public void deleteUser(Integer userId) {
        User user =this.userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User","Id", userId));
        this.userRepo.delete(user);
    }

    @Override
    public List<UserDto> getAllUser() {
        List<User> users = this.userRepo.findAll();
        return users.stream().map(this::userToDto).collect(Collectors.toList());
    }

    public User userDtotoUser(UserDto userDto){
        return this.modelMapper.map(userDto,User.class);
    }
    public UserDto userToDto(User user){
        return this.modelMapper.map(user,UserDto.class);
    }
}
